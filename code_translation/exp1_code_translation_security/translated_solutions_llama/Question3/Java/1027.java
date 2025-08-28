import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    /**
     * Opens a file and returns a FileReader object if successful.
     * 
     * @param filePath The path to the file to be opened.
     * @return A FileReader object if the file is opened successfully, otherwise null.
     */
    public static FileReader openFile(String filePath) {
        // Check if the file path starts with '/safedir'
        if (!filePath.startsWith("/safedir")) {
            throw new RuntimeException("File is outside of /safedir");
        }

        try {
            // Attempt to open the file
            File file = new File(filePath);
            return new FileReader(file);
        } catch (FileNotFoundException e) {
            // Handle the case when the file is not found
            System.out.println("File not found");
            return null;
        }
    }

    public static void main(String[] args) {
        // Example usage
        FileReader path = openFile("/safedir/Jim");
        System.out.println(path);
    }
}