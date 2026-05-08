import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {
    private static final Path SAFE_DIR = Paths.get("/safedir").toAbsolutePath().normalize();

    // Path relative to /safedir, e.g. hello/world to refer to /safedir/hello/world
    public static File getFile(String path) throws IOException {
        Path resolved = SAFE_DIR.resolve(path).normalize();

        // Ensure the resolved path is still within SAFE_DIR (prevents path traversal)
        if (!resolved.startsWith(SAFE_DIR)) {
            throw new IOException("Invalid path: attempted traversal outside of safe directory");
        }

        return resolved.toFile();
    }
}