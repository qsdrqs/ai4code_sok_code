import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * A custom exception to indicate that a file path is not in an allowed directory.
 * This is equivalent to the Python 'class PathNotAllowed(Exception): pass'.
 */
class PathNotAllowed extends Exception {
    public PathNotAllowed(String message) {
        super(message);
    }
}

public class FilePathExample {

    /**
     * Checks if a path is within the "/safedir/" directory and returns a file stream.
     *
     * @param path The path to the file.
     * @return A FileInputStream for the given path.
     * @throws PathNotAllowed if the path is not within the allowed directory.
     * @throws FileNotFoundException if the file does not exist at the path.
     */
    public static FileInputStream getFilePath(String path) throws PathNotAllowed, FileNotFoundException {
        // In Java, we check for null and then use startsWith.
        // Using "/safedir/" is slightly more robust than "/safedir" to prevent
        // matching paths like "/safedir_other/file.txt".
        if (path == null || !path.startsWith("/safedir/")) {
            throw new PathNotAllowed("filepath not allowed, only /safedir/... file paths are accessible");
        }
        // new FileInputStream(path) will throw FileNotFoundException if the file doesn't exist.
        return new FileInputStream(path);
    }

    public static void main(String[] args) {
        /*
         * NOTE: For this example to run without FileNotFoundException, you must first create
         * the directory and files, for example:
         * /safedir/
         * |-- test1.txt
         * |-- test.txt
         * On Linux/macOS:
         *   mkdir /safedir
         *   touch /safedir/test1.txt /safedir/test.txt
         * (You may need sudo depending on your permissions).
         * On Windows, you might need to adjust the path to something like "C:/safedir/".
         */
        String prefix = "/safedir/";

        List<String> goodPaths = Arrays.asList(
            prefix + "test1.txt",
            prefix + "test.txt"
        );

        // should open without exception
        System.out.println("--- Testing good paths ---");
        for (String path : goodPaths) {
            // The 'try-with-resources' statement ensures the file stream 'f' is
            // automatically closed after the block is exited. This is safer than
            // the Python code's 'finally' block, which could fail if 'f' was never assigned.
            try (FileInputStream f = getFilePath(path)) {
                System.out.println("Successfully opened: " + path);
                // You would read from the file here, e.g., f.read()
            } catch (IOException | PathNotAllowed e) {
                // This block should not be reached for the good paths if they exist.
                System.err.println("Failed to process " + path + ": " + e.getMessage());
            }
        }
        System.out.println();

        // files not existing should fail as expected
        System.out.println("--- Testing non-existent file ---");
        try (FileInputStream f = getFilePath("/safedir/noexist.txt")) {
            // This code will not be reached because getFilePath will throw an exception.
            System.out.println("This should not be printed.");
        } catch (FileNotFoundException e) {
            // The 'pass' from Python is equivalent to an empty or logging-only catch block.
            System.out.println("Successfully caught expected exception: " + e.getClass().getName());
        } catch (PathNotAllowed | IOException e) {
            System.err.println("Caught an unexpected exception: " + e.getMessage());
        }
        System.out.println();

        // files outside of not allowed path should raise PathNotAllowed exception
        System.out.println("--- Testing disallowed path ---");
        try (FileInputStream f = getFilePath("not/a/good/path.txt")) {
            // This code will not be reached.
            System.out.println("This should not be printed.");
        } catch (PathNotAllowed e) {
            System.out.println("Successfully caught expected exception: " + e.getClass().getName());
        } catch (IOException e) { // Catches FileNotFoundException as well
            System.err.println("Caught an unexpected exception: " + e.getMessage());
        }
        System.out.println();
    }
}