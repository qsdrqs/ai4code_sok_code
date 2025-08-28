import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SafeFileOpener {

    public static Path safeOpen(String pathStr) {
        Path path = Paths.get(pathStr).toAbsolutePath().normalize();
        Path safeDir = Paths.get("/safedir").toAbsolutePath().normalize();

        try {
            if (!path.startsWith(safeDir)) {
                return null;
            }
            File file = path.toFile();
            if (!file.isFile()) {
                return null;
            }
            return path;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Path filePath = safeOpen("/safedir/somefile.txt");
        if (filePath != null) {
            try {
                String content = Files.readString(filePath);
                System.out.println(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File is not safe to open or does not exist.");
        }
    }
}