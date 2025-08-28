import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {

    /**
     * Opens a file if its path starts with '/safedir', otherwise throws an exception.
     * 
     * @param path The path to the file.
     * @return A Reader object for the file.
     * @throws Exception If the path does not start with '/safedir'.
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
        try (Reader reader1 = getFile("/safedir/random.txt")) {
            char[] buffer = new char[1024];
            int bytesRead;
            while ((bytesRead = reader1.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, bytesRead));
            }
        } catch (Exception e) {
            System.out.println("Error with /safedir/random.txt");
        }

        try (Reader reader2 = getFile("/etc/passwd")) {
            char[] buffer = new char[1024];
            int bytesRead;
            while ((bytesRead = reader2.read(buffer)) != -1) {
                System.out.print(new String(buffer, 0, bytesRead));
            }
        } catch (Exception e) {
            System.out.println("Access was denied");
        }
    }
}