import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SafeFileOpener {

    /**
     * Returns a Path object to a file inside `/safedir` if the file exists and is a plain file.
     * If the file does not exist, is not inside `/safedir`, or is a symlink to a file outside of `/safedir`, returns null.
     *
     * @param path The path to the file to be checked.
     * @return A Path object if the file is safe to open, null otherwise.
     */
    public static Path safeOpen(String path) {
        Path filePath = Paths.get(path).toAbsolutePath();
        Path safeDir = Paths.get("/safedir").toAbsolutePath();

        // Check if the file is inside /safedir
        if (!filePath.startsWith(safeDir)) {
            return null;
        }

        // Check if the file is a plain file (not a directory)
        File file = filePath.toFile();
        if (!file.isFile()) {
            return null;
        }

        // Check if the file is a symlink and if it points outside /safedir
        try {
            Path realPath = Files.readSymbolicLink(filePath);
            if (!realPath.startsWith(safeDir)) {
                return null;
            }
        } catch (Exception e) {
            // If it's not a symlink or an error occurs, continue
        }

        return filePath;
    }

    public static void main(String[] args) {
        // Example usage
        Path filePath = safeOpen("/safedir/example.txt");
        if (filePath != null) {
            System.out.println("File is safe to open: " + filePath);
        } else {
            System.out.println("File is not safe to open.");
        }
    }
}