import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

public class FileHandler {

    /**
     * Gets a file relative to a safe directory.
     * <p>
     * The Python `file()` or `open()` function returns an open file object (a stream).
     * The direct Java equivalent is to return an {@link InputStream}. The caller is responsible
     * for closing this stream to prevent resource leaks, typically using a try-with-resources block.
     * <p>
     * <b>Security Note:</b> This implementation, like the original Python code, is vulnerable
     * to Path Traversal attacks (e.g., if the path is "../../../etc/passwd"). A production
     * implementation should normalize the path and verify that it remains within the intended
     * base directory.
     *
     * @param path Path relative to /safedir, e.g., "hello/world" to refer to /safedir/hello/world.
     * @return An open InputStream for the requested file.
     * @throws IOException if an I/O error occurs when opening the file.
     * @throws InvalidPathException if the path string cannot be converted to a Path.
     */
    public static InputStream getFile(String path) throws IOException {
        // Construct the full path by joining the base directory and the relative path.
        // Paths.get() is a safe and platform-aware way to handle file paths.
        Path fullPath = Paths.get("/safedir", path);

        // The Python code returns an open file object. The direct Java
        // equivalent is an InputStream, which represents an open stream of bytes.
        return Files.newInputStream(fullPath);
    }

    /**
     * Example of how to use the getFile method safely.
     */
    public static void main(String[] args) {
        String relativePath = "example.txt"; // Assume /safedir/example.txt exists
        System.out.println("Attempting to read: " + relativePath);

        // The try-with-resources statement ensures the stream is closed automatically.
        try (InputStream stream = getFile(relativePath)) {
            // We can now read from the stream.
            // For this example, we'll just print the first byte.
            int firstByte = stream.read();
            if (firstByte != -1) {
                System.out.println("Successfully opened file. First byte: " + (char)firstByte);
            } else {
                System.out.println("File is empty.");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            // In a real application, you would handle this error more gracefully.
            // e.printStackTrace();
        }
    }
}