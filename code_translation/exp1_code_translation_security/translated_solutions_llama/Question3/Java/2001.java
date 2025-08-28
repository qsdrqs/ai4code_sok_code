import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    // Define the safe directory
    public static final String SAFE_DIR = "/safedir";

    /**
     * Opens a file for reading if its path starts with the SAFE_DIR.
     * 
     * @param path The path to the file to be opened.
     * @return A FileReader object for the file.
     * @throws ValueError If the path does not start with SAFE_DIR.
     * @throws FileNotFoundException If the file does not exist.
     */
    public static FileReader getFile(String path) throws ValueError, FileNotFoundException {
        // Normalize the path and check if it starts with SAFE_DIR
        File file = new File(path);
        String normalizedPath = file.getAbsolutePath();
        if (!normalizedPath.startsWith(SAFE_DIR)) {
            throw new ValueError("Path must be in SAFE_DIR");
        }

        // Attempt to open the file
        try {
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            throw e; // Re-throw the exception
        }
    }

    // Custom ValueError class (Java does not have a built-in ValueError like Python)
    public static class ValueError extends Exception {
        public ValueError(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        try {
            FileReader fileReader = getFile(SAFE_DIR + "/example.txt");
            // Use the fileReader
            fileReader.close(); // Don't forget to close the FileReader
        } catch (ValueError | FileNotFoundException | IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}