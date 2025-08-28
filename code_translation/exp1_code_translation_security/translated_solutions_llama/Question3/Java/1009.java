import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {

    private static final String SAFE_DIR = "/safedir/";

    /**
     * Opens a file for reading, ensuring its path is within the SAFE_DIR.
     * 
     * @param path The relative path to the file.
     * @return A FileReader for the file.
     * @throws Exception If the file path is not within SAFE_DIR or if there's an I/O error.
     */
    public static FileReader getFile(String path) throws Exception {
        // Construct the absolute path
        Path safePath = Paths.get(SAFE_DIR).toAbsolutePath();
        Path testPath = safePath.resolve(path).toAbsolutePath();

        // Check if testPath is within safePath
        if (!testPath.normalize().startsWith(safePath.normalize())) {
            throw new Exception(String.format("Filename %s is not in %s directory", testPath, safePath));
        }

        try {
            // Attempt to open the file for reading
            return new FileReader(new File(SAFE_DIR + path));
        } catch (FileNotFoundException e) {
            throw new Exception("File not found: " + SAFE_DIR + path, e);
        }
    }

    public static void main(String[] args) {
        try {
            FileReader reader = getFile("example.txt");
            // Use the reader
            reader.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}