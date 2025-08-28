import java.nio.file.*;
import java.io.IOException;

public class SafeFileHandler {

    /**
     * Returns a handle to a file inside `/safedir`. If the file does not exist,
     * is not inside `/safedir`, or is a symlink to a file outside `/safedir`,
     * returns `null`. Must be a plain file (no directories).
     *
     * @param path The input file path as a string.
     * @return A `Path` object if the file is valid and inside `/safedir`, otherwise `null`.
     */
    public static Path safeOpen(String path) {
        Path resolvedPath;
        try {
            // Attempt to resolve symlinks and normalize the path.
            // This will throw IOException if the file doesn't exist or is a broken symlink.
            resolvedPath = Paths.get(path).toRealPath();
        } catch (IOException e) {
            // If the file doesn't exist or symlink can't be resolved,
            // fall back to resolving the path without following symlinks.
            resolvedPath = Paths.get(path).toAbsolutePath().normalize();
        }

        // Define the safe directory
        Path safeDir = Paths.get("/safedir").normalize();

        // Check if the resolved path is inside `/safedir`
        if (!resolvedPath.startsWith(safeDir)) {
            return null;
        }

        // Check if the resolved path is a regular file (not a directory or symlink to a directory)
        if (!Files.isRegularFile(resolvedPath)) {
            return null;
        }

        // Return the resolved path if all checks pass
        return resolvedPath;
    }
}