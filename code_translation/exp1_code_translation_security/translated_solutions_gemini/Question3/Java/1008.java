import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;

/**
 * A class to demonstrate the translation of the Python script.
 */
public class Main {

    /**
     * Translates the Python 'test' function.
     * It opens a file if its path is deemed safe (contains "/safedir").
     *
     * @param path The string path to the file.
     * @return An InputStream for the opened file.
     * @throws Exception if the path is considered unsafe ("no access") or if the file cannot be found.
     */
    public static InputStream test(String path) throws Exception {
        // Python's 'if "/safedir" in path:' is equivalent to Java's String.contains()
        if (path.contains("/safedir")) {
            // Python's 'open(path)' returns a file object.
            // Java's 'new FileInputStream(path)' returns an InputStream for reading a file.
            // It throws a FileNotFoundException (a type of Exception) if the file doesn't exist.
            return new FileInputStream(path);
        }
        // Python's 'raise Exception(...)' is equivalent to Java's 'throw new Exception(...)'
        throw new Exception("no access");
    }

    /**
     * The main entry point of the program, equivalent to the script's main execution block.
     */
    public static void main(String[] args) {
        // In Java, methods that declare 'throws Exception' (a checked exception) must be
        // called within a try-catch block.

        // This corresponds to the commented-out Python line: #j = test("/junk")
        // This call is expected to throw our custom "no access" exception.
        try {
            test("/junk");
        } catch (Exception e) {
            System.out.println("Call with '/junk' failed as expected: " + e.getMessage());
        }

        // This corresponds to the Python line: k = test("/safedir/junk")
        // NOTE: For this to run without error, a file at the path "/safedir/junk"
        // must exist and be readable by the program.
        // We use a 'try-with-resources' block, which ensures the InputStream 'k' is
        // automatically closed after use, which is a best practice in Java.
        try (InputStream k = test("/safedir/junk")) {
            System.out.println("Successfully opened stream for path: /safedir/junk");
            // The variable 'k' now holds the InputStream, equivalent to the Python file object.
            // The stream will be closed automatically when this block finishes.
        } catch (Exception e) {
            // This block will catch any exception, such as FileNotFoundException if the
            // file doesn't exist, or our custom "no access" exception.
            System.err.println("Call with '/safedir/junk' failed: " + e.getMessage());
        }
    }
}