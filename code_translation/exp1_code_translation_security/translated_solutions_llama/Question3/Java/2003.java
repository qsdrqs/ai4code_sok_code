import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    /**
     * Loads a file and returns a FileReader if the file path is safe.
     * 
     * @param pathStr The string representation of the file path.
     * @return A FileReader for the file if the path is safe.
     * @throws Exception If the file path is not safe or if there's an I/O error.
     */
    public static FileReader load(String pathStr) throws Exception {
        Path path = Paths.get(pathStr).toAbsolutePath();
        String[] pathParts = path.toString().split(File.separator);
        
        if (pathParts.length < 2 || !pathParts[1].equals("safedir")) {
            throw new Exception("Cannot access unsafe file");
        }
        
        try {
            return new FileReader(path.toFile());
        } catch (FileNotFoundException e) {
            throw new Exception("File not found", e);
        }
    }

    public static void main(String[] args) {
        try {
            // Load and print the content of a safe file
            FileReader safeFile = load("/safedir/foo.txt");
            char[] buffer = new char[1024];
            int bytesRead;
            StringBuilder content = new StringBuilder();
            while ((bytesRead = safeFile.read(buffer)) != -1) {
                content.append(buffer, 0, bytesRead);
            }
            safeFile.close();
            System.out.println(content.toString());

            // Attempt to load and print the content of an unsafe file
            FileReader unsafeFile = load("/tmp/foo.txt");
            buffer = new char[1024];
            content = new StringBuilder();
            while ((bytesRead = unsafeFile.read(buffer)) != -1) {
                content.append(buffer, 0, bytesRead);
            }
            unsafeFile.close();
            System.out.println(content.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}