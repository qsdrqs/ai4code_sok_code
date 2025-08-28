import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Creates a file object for the given path if it starts with "/safedir".
 */
public class Main {

    /**
     * Creates a file reader object for the given path if it starts with "/safedir".
     * 
     * @param path The path to check and create a file reader for.
     * @return A FileReader object if the path starts with "/safedir", otherwise null.
     */
    public static FileReader createFileObject(String path) {
        if (path.startsWith("/safedir")) {
            try {
                return new FileReader(path);
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        FileReader fileReader = createFileObject("/etc/passwd");
        if (fileReader != null) {
            System.out.println("File reader created successfully.");
            // Don't forget to close the FileReader when you're done with it
            try {
                fileReader.close();
            } catch (Exception e) {
                System.err.println("Error closing FileReader: " + e.getMessage());
            }
        } else {
            System.out.println("File reader not created.");
        }
    }
}