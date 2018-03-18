# session-info

## Design Approach:
The code has been designed to take in a log file which contains ',' separated session id and the corresponding timestamp as an input through command line argument and finds the session id with the most number of visits in a time window of 1 hour. A HashMap is created to process the input with session id being the key and the timestamps belonging to the session id as an ArrayList. The HashMap is then iterated over each key to get the corresponding list of timestamps logged. Further, this list is iterated over to compute the number of visits in a 1 hour window with the each timestamp in the list. A count of maximum visits `maxCount` is maintained to store the most number of visits by any session id until the current computation. The corresponding user is store in `maxUser`. As the number of visits by a particular session id is computed for the timeframe, the same is eventually updated with the count and the last timestamp in the `sessionCount` HashMap if it's greater than the previous counts encountered.

Once the loop terminates, the `sessionCount` has the count of the maximum visits and last timestamp by each session id. The session id with the largest visits overall is stored in `maxUser` and it's details are extracted and displayed to the standard output.

## Assumptions Made:
None

## Test Cases Considered:
Corner cases include a scenario when two session id have the same visit count but one has greater timestamp value. Also, the innermost loop runs one more than the size of the timestamp list to ensure the values computed for the last index are successfully processed.

## Running the Program
1. Download or clone the repository to a local drive
1. Extract the package and navigate to src directory
1. Compile the source code: `javac SessionInfo.java`
1. Choose either of option 1, 2  or 3

### Option 1. (Run compiled source directly)
1. Run: `java SessionInfo <path-to-log-file>`

### Option 2. (Run from executable)
1. Create executable: `jar cvfm session-info.jar META-INF/MANIFEST.MF *.class`
1. Run executable: `java -jar session-info.jar <path-to-log-file>`

### Option 3. (Run using the executable provided)
1. Navigate to the parent directory
2. Run executable: `java -jar session-info.jar <path-to-log-file>`
