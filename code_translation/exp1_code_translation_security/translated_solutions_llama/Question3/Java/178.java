import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    // Define the allowed path
    private static final String ALLOWED_PATH = "/safedir";

    /**
     * Attempts to open a file if its path starts with the allowed path.
     * 
     * @param path The path of the file to be opened.
     * @return A Scanner object for the file if the path is allowed, otherwise an error message.
     */
    public static Object getFile(String path) {
        // Check if the path starts with the allowed path
        if (!path.startsWith(ALLOWED_PATH)) {
            return "Not allowed to get file from this dir";
        }

        try {
            // Attempt to open the file
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            return scanner;
        } catch (FileNotFoundException e) {
            // Handle the case when the file does not exist
            return "File not found";
        }
    }

    public static void main(String[] args) {
        // Example usage
        Object result = getFile("/safedir/test/path");
        if (result instanceof String) {
            System.out.println(result);
        } else {
            // If you want to read from the file
            Scanner scanner = (Scanner) result;
            // Example: Read and print the first line
            if (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close(); // Don't forget to close the scanner
        }
    }
}