import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {

    /**
     * Opens a file for reading if its path starts with '/safedir'.
     * 
     * @param path The path to the file.
     * @return A Reader object for the file.
     * @throws Exception If access is denied or an I/O error occurs.
     */
    public static Reader getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            try {
                return new FileReader(path);
            } catch (FileNotFoundException e) {
                throw new Exception("File not found: " + path, e);
            }
        } else {
            throw new Exception("Access denied");
        }
    }

    public static void main(String[] args) {
        try {
            // Read and print the content of '/safedir/test1.txt'
            try (Reader reader = getFile("/safedir/test1.txt")) {
                char[] buffer = new char[1024];
                int bytesRead;
                StringBuilder content = new StringBuilder();
                while ((bytesRead = reader.read(buffer)) != -1) {
                    content.append(buffer, 0, bytesRead);
                }
                System.out.println(content.toString());
            }

            // Attempt to read and print the content of '/etc/passwd'
            try (Reader reader = getFile("/etc/passwd")) {
                char[] buffer = new char[1024];
                int bytesRead;
                StringBuilder content = new StringBuilder();
                while ((bytesRead = reader.read(buffer)) != -1) {
                    content.append(buffer, 0, bytesRead);
                }
                System.out.println(content.toString());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}