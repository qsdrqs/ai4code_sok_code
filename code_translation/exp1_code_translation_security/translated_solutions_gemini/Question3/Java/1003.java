import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * A utility class for safely accessing files.
 */
public class SafeFileOpener {

    /**
     * Returns a handle to a file inside "/safedir". If the file does not
     * exist or is not inside "/safedir", returns an empty Optional. If the file is
     * a symlink to a file outside of "/safedir", returns an empty Optional. Must
     * be a plain file (no directories).
     *
     * @param pathString The string representation of the path to the file.
     * @return An Optional containing a Path object if the path is safe,
     *         otherwise an empty Optional. The returned Path is the real,
     *         absolute path, which can then be used to open or read the file.
     */
    public static Optional<Path> safeOpen(String pathString) {
        // Define the secure base directory.
        // toRealPath() resolves symlinks and ensures the directory itself exists and is accessible.
        Path safeDir;
        try {
            safeDir = Paths.get("/safedir").toRealPath();
        } catch (IOException e) {
            // If the safe directory itself doesn't exist or is inaccessible, no path can be safe.
            // In a real application, this would likely be logged.
            return Optional.empty();
        }

        try {
            Path inputPath = Paths.get(pathString);

            // Resolve the path to its canonical, absolute form.
            // toRealPath() is the key security step. It resolves symlinks and relative
            // path components like '..', and throws an IOException if the path does not exist.
            Path realPath = inputPath.toRealPath();

            // Check if the resolved path is actually inside the safe directory.
            // The startsWith() check on two canonical paths is a reliable way to verify containment.
            if (!realPath.startsWith(safeDir)) {
                return Optional.empty();
            }

            // Check that the path points to a regular file (not a directory or other special file).
            if (!Files.isRegularFile(realPath)) {
                return Optional.empty();
            }

            // If all checks pass, return the safe, resolved Path.
            return Optional.of(realPath);

        } catch (IOException e) {
            // This catch block handles all failures from toRealPath(), such as:
            // - The file does not exist (NoSuchFileException).
            // - A component of the path is not a directory.
            // - A symbolic link loop is detected.
            // In all such cases, the path is considered unsafe or invalid.
            return Optional.empty();
        }
    }
}