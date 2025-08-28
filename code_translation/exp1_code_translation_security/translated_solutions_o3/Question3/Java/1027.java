import java.io.FileNotFoundException;
import java.io.FileReader;

public class SafeFileOpener {

    /**
     * Attempts to open a file located in “/safedir”.
     *
     * @param filePath path to the file
     * @return FileReader if the file is found, otherwise null
     * @throws RuntimeException if the file path is outside “/safedir”
     */
    public static FileReader openFile(String filePath) {
        // Validate that the file is under the allowed directory
        if (!filePath.startsWith("/safedir")) {
            throw new RuntimeException("File is outside of /safedir");
        }

        // Attempt to open the file
        try {
            return new FileReader(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            return null;   // Same behavior as Python’s None on failure
        }
    }

    public static void main(String[] args) {
        FileReader reader = openFile("/safedir/Jim");
        System.out.println(reader);   // Prints the FileReader object or null
    }
}