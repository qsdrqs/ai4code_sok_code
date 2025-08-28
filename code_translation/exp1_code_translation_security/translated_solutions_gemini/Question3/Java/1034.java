import java.io.FileReader;
import java.io.IOException;

/**
 * This class is a Java translation of the provided Python script.
 * It includes methods to safely open a file from a specific directory.
 */
public class FileHandler {

    /**
     * The main method that executes the program.
     * It calls getFileObject and prints the result, mimicking the Python script's behavior.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // To make this code runnable, you must create a directory named "safedir"
        // in the root of your execution path (e.g., your project's main directory)
        // and place a file named "test1.txt" inside it.
        FileReader fileObject = getFileObject("/safedir/test1.txt");

        // Printing the FileReader object in Java, similar to printing the file object in Python.
        // The output will be the object's class name and a hash code, e.g., "java.io.FileReader@1b6d3586".
        System.out.println(fileObject);

        // In a real-world application, it's crucial to close the FileReader to release
        // system resources, typically using a try-with-resources block.
        // However, to strictly match the Python script's behavior (which exits
        // without explicitly closing the file), we also omit an explicit close() call here.
        // The operating system will reclaim the file handle upon program termination.
    }

    /**
     * Attempts to open a file at the given path after validating the directory.
     * This is a translation of the Python 'get_file_object' function.
     *
     * @param path The path to the file.
     * @return A FileReader object for the specified file.
     */
    public static FileReader getFileObject(String path) {
        // First, check if the path is valid according to our rule.
        if (checkDir(path)) {
            try {
                // Equivalent to Python's open(path, 'r').
                // It creates a character stream for reading from the file.
                return new FileReader(path);
            } catch (IOException e) {
                // Equivalent to Python's 'except IOError:'.
                System.out.println("Error");
                // Equivalent to 'sys.exit()'. Terminates the Java Virtual Machine.
                System.exit(1);
            }
        } else {
            // If the directory check fails, exit the program.
            System.exit(1);
        }
        // This line is unreachable because the program will either return from the
        // try block or exit. It's included to satisfy the Java compiler's
        // requirement that a method with a non-void return type must always return a value.
        return null;
    }

    /**
     * Checks if the given path starts with the "/safedir" prefix.
     * This is a translation of the Python 'check_dir' function.
     *
     * @param path The file path to check.
     * @return true if the path starts with "/safedir", false otherwise.
     */
    public static boolean checkDir(String path) {
        // The original Python code checks character by character.
        // In Java, String.startsWith() is the modern, safe, and idiomatic equivalent.
        // We also check for null to prevent a NullPointerException.
        if (path == null) {
            return false;
        }
        return path.startsWith("/safedir");
    }
}