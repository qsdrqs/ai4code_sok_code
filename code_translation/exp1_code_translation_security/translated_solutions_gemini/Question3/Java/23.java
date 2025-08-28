import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class contains the Java translation of the provided Python code.
 */
public class Main {

    /**
     * Translated from the Python function `thing`.
     * It checks if a path contains "/safedir".
     * If it does, it attempts to open a file stream to that path.
     * If it doesn't, it prints an error and returns null.
     *
     * @param path The file path to check.
     * @return A FileInputStream for the path if it's valid, otherwise null.
     * @throws FileNotFoundException if the path is in "/safedir" but the file does not exist.
     */
    public static FileInputStream thing(String path) throws FileNotFoundException {
        // In Python: if("/safedir" in path):
        if (path.contains("/safedir")) {
            // In Python: return open(path)
            // The equivalent in Java is to create a new FileInputStream.
            // This can throw a FileNotFoundException, which is a checked exception in Java,
            // so we declare it in the method signature. This mirrors the Python behavior
            // where open() would raise an IOError if the file doesn't exist.
            return new FileInputStream(path);
        } else {
            // In Python: print("invalid path") and return (implicitly None)
            System.out.println("invalid path");
            return null;
        }
    }

    public static void main(String[] args) {
        // The main method acts as the test script from the Python code.
        // Note: For the "good path" test to succeed without an error, the file
        // at that path must actually exist.

        // test good path
        FileInputStream f1 = null;
        try {
            // In Python: f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt")
            f1 = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
        } catch (FileNotFoundException e) {
            // This block handles the case where the file doesn't exist,
            // similar to how Python would raise a FileNotFoundError.
            System.out.println("Error opening file for Result 1: " + e.getMessage());
        }
        System.out.println("\nResult 1:");
        System.out.println(f1); // Prints the object representation or null

        // test bad path
        FileInputStream f2 = null;
        try {
            // In Python: f = thing("/home/codex/Documents/User_Study_UI/random.txt")
            f2 = thing("/home/codex/Documents/User_Study_UI/random.txt");
        } catch (FileNotFoundException e) {
            // This catch block is not expected to be reached for the "bad path" test,
            // as the `thing` method returns null before trying to open the file.
            System.out.println("An unexpected error occurred for Result 2: " + e.getMessage());
        }
        System.out.println("\nResult 2:");
        System.out.println(f2); // Prints null, as returned by the method

        // It is good practice in Java to close file streams to release system resources.
        // The original Python script did not explicitly close the file, so for a direct
        // translation, we omit it. However, in a real application, you should use a
        // try-with-resources block or a finally block to close the streams.
        // Example:
        // if (f1 != null) {
        //     try {
        //         f1.close();
        //     } catch (IOException e) {
        //         e.printStackTrace();
        //     }
        // }
    }
}