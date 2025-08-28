import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SafeFileHandler {
    
    /**
     * Return a handle to a file inside "/safedir". If the file does not
     * exist or is not inside "/safedir", returns null. If the file is
     * a symlink to a file outside of "/safedir", returns null. Must
     * be a plain file (no directories).
     *
     * Returns a Path object, which you can then use with Files.readString(),
     * Files.newBufferedReader(), or other file operations.
     */
    public static Path safeOpen(String pathString) {
        try {
            Path path = Paths.get(pathString).toRealPath();
            Path safeDir = Paths.get("/safedir").toRealPath();
            
            // Check if path is inside /safedir
            if (!path.startsWith(safeDir)) {
                return null;
            }
            
            // Check if it's a regular file (not directory)
            if (!Files.isRegularFile(path)) {
                return null;
            }
            
            return path;
        } catch (IOException e) {
            // This handles cases where the path doesn't exist or can't be resolved
            return null;
        }
    }
}