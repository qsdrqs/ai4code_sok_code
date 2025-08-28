import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A Java class that safely loads a file, ensuring it resides within a designated safe directory.
 * This is a translation of the provided Python script.
 */
public class SafeFileLoader {

    // The designated safe directory.
    private static final String SAFE_DIR = "/safedir";

    /**
     * Safely opens a file for reading, ensuring it is within the SAFE_DIR.
     * This prevents path traversal attacks (e.g., using "../").
     *
     * @param fname The name/path of the file to load.
     * @return A BufferedReader for the file if it's safe to load, otherwise null.
     * @throws IOException if an I/O error occurs during path resolution or file opening.
     */
    public static BufferedReader safeLoadFile(String fname) throws IOException {
        // Create a Path object from the filename string.
        Path fpath = Paths.get(fname);

        // Get the canonical, absolute path. This resolves symlinks and ".." components.
        // This is the critical security step, equivalent to Python's os.path.realpath().
        Path realPath;
        try {
            realPath = fpath.toRealPath();
        } catch (IOException e) {
            // This can happen if the file doesn't exist or there's a permissions issue.
            System.out.println("FAILED: Could not resolve path. " + e.getMessage());
            return null;
        }

        // Check if the canonical path starts with the safe directory.
        // We use toString() to perform a string comparison.
        if (!realPath.toString().startsWith(SAFE_DIR)) {
            System.out.println("FAILED: Path is outside the safe directory.");
            return null;
        }

        // If the path is safe, return a BufferedReader for the file.
        System.out.println("Path is safe. Opening file: " + realPath);
        return Files.newBufferedReader(realPath);
    }

    /**
     * The main method, equivalent to Python's `if __name__ == "__main__":`.
     * It demonstrates the usage of the safe_load_file method.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // To run this example, you must first create the directory and file:
        // $ mkdir -p /safedir
        // $ echo "Hello from a safe file!" > /safedir/test.txt

        // The try-with-resources statement ensures the BufferedReader is closed automatically.
        try (BufferedReader reader = safeLoadFile("/safedir/test.txt")) {
            // Check if the file was loaded successfully before trying to read from it.
            if (reader != null) {
                System.out.println("File content:");
                // Read the entire file content.
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
                System.out.print(content.toString());
            }
        } catch (IOException e) {
            // Handle potential I/O errors during file reading.
            System.err.println("An error occurred while reading the file:");
            e.printStackTrace();
        }

        // --- Demonstrate a failing case (path traversal attempt) ---
        System.out.println("\n--- Testing a malicious path ---");
        try {
            // This call should fail the security check and return null.
            // The try-with-resources will handle the null without error.
            try (BufferedReader reader = safeLoadFile("/safedir/../etc/passwd")) {
                if (reader == null) {
                    System.out.println("As expected, the malicious file was not loaded.");
                }
            }
        } catch (IOException e) {
            // This catch block will execute if toRealPath() fails because the path
            // (e.g., /etc/passwd) doesn't exist or is inaccessible.
            System.err.println("Caught expected error for malicious path: " + e.getMessage());
        }
    }
}