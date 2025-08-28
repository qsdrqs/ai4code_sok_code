import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FilePathFinder {

    /**
     * Checks if a file path is located in a specific safe directory and, if so,
     * attempts to open it.
     *
     * @param path The string representing the full path to the file.
     * @return A FileInputStream for the file if it's safe, otherwise null.
     */
    public static FileInputStream findPath(String path) {
        // Define the designated safe directory
        final String safePath = "/safedir";

        // In Java, the File class can be used to extract parts of a path.
        // file.getParent() is the equivalent of Python's os.path.dirname().
        File file = new File(path);
        String dirname = file.getParent();

        // Use .equals() for string comparison. We also check if dirname is not null.
        if (safePath.equals(dirname)) {
            System.out.println("safe!");
            try {
                // In Java, opening a file for reading can be done with FileInputStream.
                // This operation can throw a FileNotFoundException, which must be handled.
                return new FileInputStream(path);
            } catch (FileNotFoundException e) {
                System.err.println("Error: File could not be opened. " + e.getMessage());
                return null;
            }
        } else {
            System.out.println("not safe!");
            // Return null, which is the Java equivalent of Python's implicit 'None' return.
            return null;
        }
    }

    /**
     * The main method to run the examples.
     */
    public static void main(String[] args) {
        // The following calls replicate the behavior of the original Python script.
        // Note: For the first call to succeed without an exception, the file
        // "/safedir/random.txt" must actually exist on the filesystem.
        findPath("/safedir/random.txt");
        findPath("/home/codex/Documents/User_Study_UI");
    }
}