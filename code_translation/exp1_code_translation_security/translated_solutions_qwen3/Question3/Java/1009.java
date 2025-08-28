import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

public class SafeFileReader {
    private static final String SAFE = "/safedir/";

    public static BufferedReader getFile(String path) throws Exception {
        // Normalize the base safe directory path
        Path safeBase = Paths.get(SAFE).normalize();

        // Resolve and normalize the test path
        Path testPath = safeBase.resolve(path).normalize();

        // Check if the test path is within the safe directory
        if (!testPath.startsWith(safeBase)) {
            throw new Exception("Filename " + testPath + " is not in " + safeBase + " directory");
        }

        // Open the file using the original path concatenation logic
        return Files.newBufferedReader(Paths.get(SAFE + path));
    }
}