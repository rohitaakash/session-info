import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SessionInfo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String logFile = args[0]; // initialize the name of log file through command line argument
        String line = null;

        Map<Integer, List<Long>> sessions = new HashMap<Integer, List<Long>>();

        try {
            FileReader fileReader = new FileReader(logFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // read through each line of the file and perform the necessary steps
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",\\s"); // split each line on the delimiter ','
                int sessionId = Integer.parseInt(parts[0]); // extract session id from the split array
                if (sessions.containsKey(sessionId)) {
                    sessions.get(sessionId).add(Long.parseLong(parts[1])); // append timestamp to a list in session id
                } else {
                    List<Long> timestamps = new ArrayList<>();
                    timestamps.add(Long.parseLong(parts[1]));
                    sessions.put(sessionId, timestamps); // add a new session id and the first timestamp value as a list
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + logFile + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + logFile + "'");
        }

        Map<Integer, List<Long>> sessionCount = new HashMap<Integer, List<Long>>();     // hashmap to store the session counts for each id
        int maxCount = 0;                                                               // the maximum number of visiting sessions till present
        int maxUser = 0;                                                                // the session id with maximum visits
        for (int id : sessions.keySet()) {
            List<Long> timestamps = sessions.get(id);                                   // extract the list of timestamps for a given session id
            Collections.sort(timestamps);                                               // sort the timestamps in ascending order
            for (int i = 0; i < timestamps.size() - 1; i++) {
                long startTime = timestamps.get(i);                                     // initialize the window start time
                int count = 1;                                                          // count to store the number of active sessions in the window

                for (int j = i + 1; j < timestamps.size()+1; j++) {
                    if (j != timestamps.size() && (timestamps.get(j) - startTime)/1000 <= 3600 ) {
                        count++;                                                        // increment count if it's within the 1 hour window
                    } else {
                        if (maxCount <= count) {
                            if (maxCount < count ||
                                    (maxCount == count && sessionCount.get(maxUser).get(1) < timestamps.get(j - 1)))
                                maxUser = id;                                           // update the session id with maximum visits when current count is higher than the max count
                                                                                        // or when the current session id has greater timestamp value when the counts are equal

                            maxCount = count;                                           // update the max count

                            // add the count and last timestamp values to sessionCount map
                            List<Long> details = new ArrayList<>();
                            details.add((long) count);
                            details.add(timestamps.get(j - 1));
                            sessionCount.put(id, details);
                        }
                        break;
                    }
                }
            }
        }

        System.out.println(maxUser + "," + sessionCount.get(maxUser).get(0) + "," + sessionCount.get(maxUser).get(1));

    }

}
