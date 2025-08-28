import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    /**
     * Opens a file if its path contains "/safedir".
     * 
     * @param path The path to the file.
     * @return A Scanner object for the file.
     * @throws Exception If the path does not contain "/safedir" or if there's an issue opening the file.
     */
    public static Scanner test(String path) throws Exception {
        if (path.contains("/safedir")) {
            try {
                return new Scanner(new File(path));
            } catch (FileNotFoundException e) {
                throw new Exception("File not found: " + e.getMessage());
            }
        }
        throw new Exception("no access");
    }

    public static void main(String[] args) {
        try {
            // Scanner j = test("/junk"); // Uncomment this line to test
            Scanner k = test("/safedir/junk");
            // Example usage: Read and print the file content
            while (k.hasNextLine()) {
                System.out.println(k.nextLine());
            }
            k.close(); // Close the Scanner
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}