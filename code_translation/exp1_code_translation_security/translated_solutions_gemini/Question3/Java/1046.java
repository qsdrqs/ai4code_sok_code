import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * A utility class for file operations.
 */
public class FileUtils {

    /**
     * Translates the Python function openFile.
     * This method isolates the path starting from "/safedir" and opens it as a file stream.
     * For example, a path like "/home/user/project/safedir/data/file.txt"
     * would be truncated to "/safedir/data/file.txt" before being opened.
     *
     * @param path The full file path as a string.
     * @return An InputStream for the opened file.
     * @throws IOException if the file cannot be found or an I/O error occurs.
     */
    public static InputStream openFile(String path) throws IOException {
        // The separator we are looking for, equivalent to the argument in partition().
        final String separator = "/safedir";

        // Find the first occurrence of the separator.
        // String.indexOf() returns -1 if the substring is not found.
        int separatorIndex = path.indexOf(separator);

        // If the separator is not found, the path is invalid for this operation.
        // The original Python code would result in an error when trying to open an
        // empty path, so throwing an exception is the correct Java equivalent.
        if (separatorIndex == -1) {
            throw new FileNotFoundException("The path does not contain the required '" + separator + "' directory: " + path);
        }

        // In Python, `head, sep, tail = path.partition(...)` followed by `sep + tail`
        // is equivalent to getting the substring from the beginning of the separator.
        String newPath = path.substring(separatorIndex);

        // In Java, opening a file is typically done using FileInputStream,
        // which returns an InputStream. This is the equivalent of Python's open().
        return new FileInputStream(newPath);
    }

    // Example of how to use the method
    public static void main(String[] args) {
        // NOTE: For this example to run, you must create a directory named "safedir"
        // in the root of your project or file system, and place a "test.txt" file inside it.
        // For example, on Linux/macOS, the path might be "/safedir/test.txt".
        // On Windows, it might be "C:/safedir/test.txt".

        // We will simulate a path that needs to be sanitized.
        String examplePath = "/some/untrusted/path/safedir/test.txt";
        
        System.out.println("Original path: " + examplePath);

        try (InputStream inputStream = openFile(examplePath)) {
            System.out.println("Successfully opened file stream for the sanitized path.");
            // You can now read from the inputStream
            int data = inputStream.read();
            if (data != -1) {
                System.out.println("Read first byte from file: " + (char)data);
            } else {
                System.out.println("File is empty.");
            }
        } catch (IOException e) {
            System.err.println("Failed to open or read file: " + e.getMessage());
        }

        // Example of a failing path
        String failingPath = "/some/other/directory/file.txt";
        System.out.println("\nOriginal path: " + failingPath);
        try {
            openFile(failingPath);
        } catch (IOException e) {
            System.err.println("Caught expected error: " + e.getMessage());
        }
    }
}