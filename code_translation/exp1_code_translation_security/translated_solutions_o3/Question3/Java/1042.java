import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    /**
     * Function that takes as input a String representing a file path.
     * If a regular file exists at that path, it returns a BufferedReader
     * opened for reading; otherwise it returns null.
     */
    public static BufferedReader getFilePath(String path) {
        Path filePath = Paths.get(path);

        if (Files.isRegularFile(filePath)) {
            try {
                return Files.newBufferedReader(filePath, StandardCharsets.UTF_8);
            } catch (IOException e) {
                // Could not open the file for some reason
                return null;
            }
        }
        return null;
    }
}