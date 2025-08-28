import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class Main {

    /**
     * Opens a file and returns a Reader object if the file path starts with '/safedir'.
     * 
     * @param path The path to the file.
     * @return A Reader object for the file.
     * @throws Exception If the file path does not start with '/safedir' or if there's an I/O error.
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
        try (Reader reader = getFile("/safedir/test1.txt")) {
            int character;
            while ((character = reader.read()) != -1) {
                System.out.print((char) character);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        // try (Reader reader = getFile("/etc/passwd")) {
        //     int character;
        //     while ((character = reader.read()) != -1) {
        //         System.out.print((char) character);
        //     }
        // } catch (Exception e) {
        //     System.err.println(e.getMessage());
        // }
    }
}