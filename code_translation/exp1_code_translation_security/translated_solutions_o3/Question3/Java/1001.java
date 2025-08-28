import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class that contains helpers for safely opening files.
 */
public final class SafeFileUtils {

    private SafeFileUtils() {
        /* Utility class – no instances allowed. */
    }

    /**
     * Returns {@code true} if the supplied path is considered “safe”.
     * A safe path:
     *   1. Starts with "/safedir"
     *   2. Does NOT contain the substring "/.."
     */
    public static boolean checkIfSafe(String path) {
        return path.startsWith("/safedir") && !path.contains("/..");
    }

    /**
     * Attempts to open a file for reading if (and only if) the path
     * passes the {@link #checkIfSafe(String)} test.
     *
     * @param filePath absolute or relative file path
     * @return a {@link BufferedReader} for the file, or {@code null}
     *         if the path is unsafe or the file cannot be opened
     */
    public static BufferedReader getFile(String filePath) {
        if (!checkIfSafe(filePath)) {
            return null;
        }

        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (IOException ex) {
            // File not found or cannot be opened
            return null;
        }
    }
}