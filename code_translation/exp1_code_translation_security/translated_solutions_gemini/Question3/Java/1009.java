import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A utility class for safe file operations.
 */
public class SafeFileOpener {

    // The designated safe directory. All file access is restricted to this directory.
    private static final String SAFE_DIR = "/safedir/";

    /**
     * Safely opens a file from within the SAFE_DIR, preventing directory traversal.
     *
     * This method works by resolving the absolute path of the requested file and
     * ensuring it is a descendant of the resolved safe directory path.
     *
     * @param path The relative path to the file provided by the user.
     * @return A BufferedReader for the requested file.
     * @throws IOException if the file does not exist or an I/O error occurs.
     * @throws SecurityException if the requested path is outside the safe directory.
     */
    public static BufferedReader getFile(String path) throws IOException {
        // Get the canonical, absolute path of the safe directory.
        Path safeDirectory = Paths.get(SAFE_DIR).toAbsolutePath().normalize();

        // Resolve the user-provided path against the safe directory.
        // The .normalize() method is crucial as it resolves ".." and "." segments.
        Path requestedPath = safeDirectory.resolve(path).toAbsolutePath().normalize();

        // Security Check:
        // Ensure the resolved path of the requested file starts with the safe directory's path.
        // This is the standard and safest way to prevent directory traversal attacks in Java.
        if (!requestedPath.startsWith(safeDirectory)) {
            throw new SecurityException(
                String.format("Filename %s is not in %s directory", requestedPath, safeDirectory)
            );
        }

        // After validation, open a reader to the file using the fully resolved and verified path.
        return Files.newBufferedReader(requestedPath);
    }
}