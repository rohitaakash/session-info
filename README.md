# session-info

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
