import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class FileOpener {
    public static Path openFile(String path) throws IOException {
        String[] parts = path.split("/safedir", 2);
        if (parts.length < 2) {
            throw new IllegalArgumentException("Path does not contain '/safedir'");
        }
        String newPath = "/safedir" + parts[1];
        Path filePath = Paths.get(newPath);
        if (!Files.exists(filePath)) {
            throw new IOException("File does not exist: " + newPath);
        }
        return filePath;
    }
}