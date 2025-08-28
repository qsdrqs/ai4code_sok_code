import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class contains the Java equivalent of the Python script.
 */
public class FileOpener {

    /**
     * Opens a file, but only if it's within the "/safedir" directory.
     * This method is the direct translation of the Python function `open_file`.
     *
     * @param filePath The path to the file to open.
     * @return A BufferedReader object for the file, or null if the file is not found.
     * @throws IllegalArgumentException if the file path is outside the allowed directory.
     */
    public static BufferedReader openFile(String filePath) {
        // In Java, we use the startsWith() method on the String class.
        if (!filePath.startsWith("/safedir")) {
            // Instead of a generic Exception, we throw a more specific one.
            // IllegalArgumentException is suitable for invalid parameters.
            throw new IllegalArgumentException("File is outside of /safedir");
        }

        try {
            // Java uses "Reader" objects to handle files. BufferedReader is an
            // efficient choice for reading text files. It wraps a FileReader.
            BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
            return fileReader;
        } catch (IOException e) {
            // IOException is the Java equivalent of Python's IOError.
            // It covers cases like the file not existing or permission errors.
            System.out.println("File not found");
            // Java uses 'null' where Python uses 'None'.
            return null;
        }
    }

    /**
     * The main method is the entry point of the Java application,
     * similar to the script-level code in the Python example.
     */
    public static void main(String[] args) {
        BufferedReader path = null;
        try {
            // Call the method, just like in Python.
            path = openFile("/safedir/Jim");

            // Print the result. If the file was opened, this will print the
            // object's representation (e.g., java.io.BufferedReader@...).
            // If not found, it will print "null".
            System.out.println(path);

        } catch (IllegalArgumentException e) {
            // This block handles the exception thrown for paths outside /safedir.
            System.err.println("Error: " + e.getMessage());
        } finally {
            // CRITICAL: In Java, it's essential to explicitly close file streams
            // to release system resources. The 'finally' block ensures this code
            // runs whether an exception occurred or not.
            if (path != null) {
                try {
                    path.close();
                } catch (IOException e) {
                    // This inner try-catch handles potential errors during the close operation itself.
                    e.printStackTrace();
                }
            }
        }
    }
}