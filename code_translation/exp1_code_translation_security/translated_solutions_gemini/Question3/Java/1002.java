import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class contains the Java translation of the provided Python code.
 * The main method executes the translated test cases.
 */
public class SafeFileOpener {

    /**
     * Safely opens a file, ensuring it resides within a "/safedir" directory at the root.
     * This method is a translation of the Python function `open_file`.
     *
     * @param filename The path to the file.
     * @return An InputStream for the file if the path is safe and the file exists.
     *         Returns null if the path is not in the allowed directory.
     * @throws FileNotFoundException if the path is safe but the file does not exist.
     */
    public static InputStream openFile(String filename) throws FileNotFoundException {
        Path path;
        try {
            // Python: path = pathlib.Path(filename).resolve()
            // Java: Normalize the path to resolve ".." and make it absolute.
            // .toAbsolutePath() ensures the path is absolute before checking components.
            // .normalize() resolves path components like "..".
            path = Paths.get(filename).toAbsolutePath().normalize();
        } catch (InvalidPathException e) {
            // The input string is not a valid path.
            return null;
        }

        // Python: parts = path.parts
        //         if len(parts) >= 2 and parts[1] == "safedir":
        //
        // Java: For an absolute path like "/safedir/file.txt", path.getNameCount() is 2
        // and path.getName(0) is "safedir". This is the equivalent check.
        // We check for getNameCount() > 0 to avoid errors on root-only paths like "/".
        if (path.getNameCount() > 0 && "safedir".equals(path.getName(0).toString())) {
            // Python: return open(path)
            // Java: Create a new FileInputStream. This will throw a FileNotFoundException
            // if the file doesn't exist, mimicking Python's open() behavior.
            return new FileInputStream(path.toFile());
        }

        // Python: return None
        return null;
    }

    /**
     * Contains tests for the openFile method, translated from the Python `test` function.
     * To run this, enable assertions in the JVM (e.g., `java -ea SafeFileOpener`).
     */
    public static void test() {
        // Test 1: Path traversal attempt
        // Python: assert open_file("/safedir/../a/b") is None
        try {
            // The path "/safedir/../a/b" normalizes to "/a/b".
            // The check for "safedir" as the first component will fail.
            // Therefore, openFile should return null.
            assert openFile("/safedir/../a/b") == null;
            System.out.println("Test 1 PASSED: Path traversal was correctly blocked.");
        } catch (FileNotFoundException e) {
            // This exception should not be thrown for this test case.
            // If it is, the test fails.
            System.err.println("Test 1 FAILED: Threw an unexpected FileNotFoundException.");
            e.printStackTrace();
        }

        // Test 2: Attempt to open a file that is not expected to exist.
        // Python:
        // try:
        //     open_file("/safedir/a")
        //     assert False
        // except:
        //     pass
        try {
            // The path "/safedir/a" is valid according to the security check.
            // The method will attempt to open the file.
            // Since the file is not expected to exist, a FileNotFoundException should be thrown.
            openFile("/safedir/a");
            // If this line is reached, it means no exception was thrown, which is an error
            // for this test case (it implies the file exists). The `assert false` mimics the Python test.
            assert false : "Test 2 FAILED: File '/safedir/a' was found, but the test expects it to be missing.";
        } catch (FileNotFoundException e) {
            // Catching the expected exception means the test passes.
            System.out.println("Test 2 PASSED: Correctly threw FileNotFoundException for a non-existent file in a safe path.");
        } catch (AssertionError e) {
            // This catches the `assert false` from above and reports the failure.
            System.err.println(e.getMessage());
        }
    }

    /**
     * Main entry point to run the tests.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("Running tests for SafeFileOpener...");
        System.out.println("NOTE: For assertions to work, run with the -ea flag (e.g., 'java -ea SafeFileOpener').");
        test();
        System.out.println("Tests finished.");
    }
}