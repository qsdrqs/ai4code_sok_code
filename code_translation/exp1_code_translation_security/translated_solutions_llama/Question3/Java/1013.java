import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    // Define the safe directory
    private static final String SAFE_DIR = "/safedir";

    /**
     * Checks if a file is within the safe directory.
     * 
     * @param filePath The path to the file to check.
     * @return True if the file is within the safe directory, false otherwise.
     */
    private static boolean isInSafeDirectory(String filePath) {
        // Normalize paths for comparison
        String safeDirNorm = SAFE_DIR.toLowerCase();
        String fileDirNorm = new File(filePath).getAbsolutePath().toLowerCase();

        // Get the directory of the file
        int lastIndex = fileDirNorm.lastIndexOf(File.separator);
        if (lastIndex != -1) {
            fileDirNorm = fileDirNorm.substring(0, lastIndex);
        }

        // Check if the file's directory is a subdirectory of the safe directory
        return fileDirNorm.startsWith(safeDirNorm);
    }

    /**
     * Opens a file if it is within the safe directory.
     * 
     * @param path The path to the file to open.
     * @return A FileReader for the file if it is within the safe directory, null otherwise.
     */
    public static FileReader openFile(String path) {
        if (isInSafeDirectory(path)) {
            try {
                return new FileReader(path);
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }
        return null;
    }

    public static void main(String[] args) {
        FileReader file = openFile("/env.sh");
        if (file != null) {
            System.out.println("File opened successfully.");
            // Don't forget to close the file reader when you're done with it
            try {
                file.close();
            } catch (Exception e) {
                System.err.println("Error closing file: " + e.getMessage());
            }
        } else {
            System.out.println("File is not within the safe directory or could not be opened.");
        }
    }
}