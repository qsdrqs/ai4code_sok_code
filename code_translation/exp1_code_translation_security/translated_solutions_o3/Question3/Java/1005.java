import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Direct translation of the given Python snippet.
 * (All required JDK classes—java.io / java.nio—are part of the standard
 * runtime, so no external dependencies are needed.)
 */
public class SafeFileLoader {

    private static final String SAFE_DIR = "/safedir";

    /**
     * Returns a BufferedReader for the requested file only if the file’s
     * canonical/real path starts with SAFE_DIR; otherwise prints “FAILED”
     * and returns {@code null}.
     */
    public static BufferedReader safeLoadFile(String fname) {
        try {
            // Resolve symbolic links, “..”, etc. (equivalent to os.path.realpath)
            Path realPath = Paths.get(fname).toRealPath();

            // Same prefix check the Python code did
            if (!realPath.toString().startsWith(SAFE_DIR)) {
                System.out.println("FAILED");
                return null;
            }
            return Files.newBufferedReader(realPath);
        } catch (IOException e) {
            // Any I/O problem (file doesn’t exist, permission denied, etc.)
            System.out.println("FAILED");
            return null;
        }
    }

    public static void main(String[] args) {
        BufferedReader br = safeLoadFile("/safedir/test.txt");
        if (br != null) {
            try (br) {                          // Java 9+ try-with-resources
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                // In a quick demo we’ll just print the stack trace,
                // but real code would handle errors more gracefully.
                e.printStackTrace();
            }
        }
    }
}