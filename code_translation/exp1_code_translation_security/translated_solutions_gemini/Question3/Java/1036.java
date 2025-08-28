import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class is a Java translation of the provided Python script.
 * It includes a method to safely access files from a specific directory
 * and a main method to demonstrate its usage.
 */
public class FileAccessExample {

    /**
     * Safely retrieves a file's InputStream, but only if it's located within the "/safedir" directory.
     * This method is a direct translation of the Python get_file function.
     *
     * @param path The string path to the file.
     * @return An InputStream for the file if access is allowed.
     * @throws Exception if the path is not in the allowed directory ("Access denied")
     *                   or if there is an I/O error opening the file.
     */
    public static InputStream getFile(String path) throws Exception {
        // In Java, path.startsWith() is the equivalent of Python's path.startswith()
        if (path.startsWith("/safedir")) {
            // java.nio.file.Files is the modern way to handle file I/O.
            // It can throw an IOException if the file doesn't exist or can't be read.
            return Files.newInputStream(Paths.get(path));
        } else {
            // Throwing an exception is the standard way to handle errors and invalid conditions.
            throw new Exception("Access denied");
        }
    }

    /**
     * The main entry point of the program.
     * It demonstrates the behavior of the getFile method.
     *
     * @param args Command line arguments (not used).
     * @throws Exception The program is expected to throw an exception on the second call,
     *                   mimicking the behavior of the original Python script.
     */
    public static void main(String[] args) throws Exception {
        // --- Dependency Setup ---
        // The original Python code assumes the file system is already set up.
        // To make this Java code runnable, we create the directory and file.
        // Note: On Windows, creating a top-level "/safedir" may fail due to permissions.
        // This code is written to be a direct translation and assumes a Unix-like file system.
        try {
            Path safeDir = Paths.get("safedir"); // Using a relative path for better portability
            if (!Files.exists(safeDir)) {
                Files.createDirectories(safeDir);
            }
            Path testFile = safeDir.resolve("test1.txt");
            Files.write(testFile, "This is the content of test1.txt.".getBytes(StandardCharsets.UTF_8));
            
            // For the demonstration, we will use a relative path for the valid case
            // and an absolute path for the invalid case, as in the original script.
            String safePath = testFile.toAbsolutePath().toString();
            String unsafePath = "/etc/passwd"; // This path remains as in the original example.

            System.out.println("Attempting to read the allowed file: " + safePath);
            // --- First Call (Successful) ---
            // In Java, it's crucial to close streams to prevent resource leaks.
            // The try-with-resources statement ensures the InputStream is automatically closed.
            try (InputStream fileStream = getFile(safePath)) {
                // Read all bytes from the stream and convert to a String to print.
                String content = new String(fileStream.readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(content);
            }

            System.out.println("\nAttempting to read the denied file: " + unsafePath);
            // --- Second Call (Throws Exception) ---
            // This call will fail because the path does not start with "/safedir".
            // The program will terminate here with an "Access denied" exception,
            // just like the Python script.
            try (InputStream fileStream = getFile(unsafePath)) {
                String content = new String(fileStream.readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(content);
            }

        } catch (IOException e) {
            System.err.println("Failed to set up dummy files for the example. Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}