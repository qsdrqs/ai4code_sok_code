import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SafeFileAccess {
    /**
     * Return a handle to a file inside `/safedir`. If the file does not
     * exist or is not inside `/safedir`, returns `null`. If the file is
     * a symlink to a file outside of `/safedir`, returns `null`. Must
     * be a plain file (no directories).
     *
     * Returns a {@link Path} object, which you can then use to open
     * the file or read its contents.
     */
    public static Path safeOpen(String pathStr) {
        Path path;
        try {
            // toRealPath() is the equivalent of pathlib's resolve():
            // it makes the path absolute and follows symlinks.
            // It throws IOException if the file does not exist, which
            // matches the Python behavior of returning None for
            // non-existent files (via the later is_file() check).
            path = Paths.get(pathStr).toRealPath();
        } catch (IOException e) {
            return null;
        }

        Path safedir = Paths.get("/safedir");
        // Path.startsWith() compares path components, so this is
        // equivalent to pathlib's relative_to() check. For example,
        // "/safedirx/foo" does NOT start with "/safedir".
        if (!path.startsWith(safedir)) {
            return null;
        }

        // Ensure it's a plain file (not a directory, device, etc.).
        if (!Files.isRegularFile(path)) {
            return null;
        }

        return path;
    }
}