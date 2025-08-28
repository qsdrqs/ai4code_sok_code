import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SafeFileLoader {

    /**
     * Safely loads a file, ensuring it resides within a "/safedir" directory
     * at the root of the filesystem.
     *
     * @param pathStr The string representation of the file path.
     * @return An InputStream for the file if it is safe to access.
     * @throws SecurityException if the path is not within the "/safedir" directory.
     * @throws IOException if an I/O error occurs when opening the file.
     */
    public static InputStream load(String pathStr) throws IOException {
        // In Java, Paths.get() is the equivalent of Python's Path().
        // .toAbsolutePath().normalize() is the equivalent of .resolve()
        // for creating a canonical, absolute path.
        Path path = Paths.get(pathStr).toAbsolutePath().normalize();

        // Python's path.parts for "/safedir/foo.txt" is ('/', 'safedir', 'foo.txt').
        // Java's Path object for the same path has a getNameCount() of 2,
        // with getName(0) being "safedir" and getName(1) being "foo.txt".
        // The root '/' is not included in the name count.
        // So, len(path.parts) < 2 is equivalent to path.getNameCount() < 1.
        // And path.parts[1] is equivalent to path.getName(0).
        if (path.getNameCount() < 2 || !path.getName(0).toString().equals("safedir")) {
            throw new SecurityException("Cannot access unsafe file");
        }

        return Files.newInputStream(path);
    }

    public static void main(String[] args) {
        // --- Setup: Create dummy files for demonstration ---
        // This part is for making the example runnable.
        // In a real scenario, these files would already exist.
        try {
            Files.createDirectories(Paths.get("/safedir"));
            Files.write(Paths.get("/safedir/foo.txt"), "content from safedir".getBytes(StandardCharsets.UTF_8));
            Files.createDirectories(Paths.get("/tmp"));
            Files.write(Paths.get("/tmp/foo.txt"), "content from tmp".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.err.println("Failed to set up dummy files: " + e.getMessage());
            // On some systems (like Windows), creating directories at the root might fail.
            // The logic can still be understood without running this setup.
        }
        // --- End Setup ---


        // 1. Test the valid case
        System.out.println("Attempting to load /safedir/foo.txt...");
        try (InputStream safeStream = load("/safedir/foo.txt")) {
            String content = new String(safeStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("Success: " + content);
        } catch (Exception e) {
            System.err.println("Error loading safe file: " + e.getMessage());
        }

        System.out.println("\n----------------------------------\n");

        // 2. Test the invalid case
        System.out.println("Attempting to load /tmp/foo.txt...");
        try (InputStream unsafeStream = load("/tmp/foo.txt")) {
            // This line should not be reached
            String content = new String(unsafeStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("Success: " + content);
        } catch (Exception e) {
            System.err.println("Caught expected exception: " + e.getClass().getName() + " - " + e.getMessage());
        }
    }
}