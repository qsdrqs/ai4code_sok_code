import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /**
     * Opens a file if the path starts with "/safedir".
     * 
     * @param path The path to the file.
     * @return A FileReader object for the file.
     * @throws ValueError If the path does not start with "/safedir".
     * @throws FileNotFoundException If the file does not exist.
     */
    public static FileReader openFile(String path) throws ValueError, FileNotFoundException {
        if (path.startsWith("/safedir")) {
            return new FileReader(path);
        } else {
            throw new ValueError("Path must start with /safedir");
        }
    }

    public static void main(String[] args) {
        try {
            FileReader res1 = openFile("/safedir/random.txt");
            System.out.println(res1);
            // Note: In Java, we typically close resources after use to prevent resource leaks.
            // res1.close(); // Uncomment this line when you're done with the file.

            // This will throw an exception
            openFile("blah");
        } catch (ValueError e) {
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}

// Custom ValueError class
class ValueError extends Exception {
    public ValueError(String message) {
        super(message);
    }
}