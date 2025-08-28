import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class SafeFileUtil {

    // Equivalent to Python’s SAFE_DIR = "/safedir"
    private static final Path SAFE_DIR = Paths.get("/safedir").toAbsolutePath().normalize();

    /**
     * Opens the file located at {@code path} for reading iff it is
     * inside the {@code SAFE_DIR}.  Otherwise an {@link IllegalArgumentException}
     * is thrown, mirroring the Python implementation’s ValueError.
     *
     * @param path absolute or relative path to the file
     * @return BufferedReader for the requested file
     * @throws IOException if the file cannot be opened
     * @throws IllegalArgumentException if {@code path} is not under {@code SAFE_DIR}
     */
    public static BufferedReader getFile(String path) throws IOException {
        Path target = Paths.get(path).toAbsolutePath().normalize();

        if (!target.startsWith(SAFE_DIR)) {
            throw new IllegalArgumentException("Path must be in SAFE_DIR");
        }

        // Open the file using the default UTF-8 encoding (adjust if needed)
        return Files.newBufferedReader(target, StandardCharsets.UTF_8);
    }

    private SafeFileUtil() {
        // prevent instantiation
    }
}