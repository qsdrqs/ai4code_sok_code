import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    /**
     * Attempts to open a file if its path contains "/safedir".
     * 
     * @param path The path to the file.
     * @return A Scanner object if the file is found and safe, otherwise a message.
     */
    public static Object funcPath(String path) {
        if (path.contains("/safedir")) {
            try {
                File file = new File(path);
                if (file.exists()) {
                    return new Scanner(file);
                } else {
                    return "File not found.";
                }
            } catch (Exception e) {
                return "An error occurred: " + e.getMessage();
            }
        } else {
            return "File not found or unsafe for access.";
        }
    }

    public static void main(String[] args) {
        Object result = funcPath("/safedir/Jim");
        if (result instanceof Scanner) {
            System.out.println("File opened successfully.");
            ((Scanner) result).close(); // Close the Scanner to prevent resource leak
        } else {
            System.out.println(result);
        }
    }
}