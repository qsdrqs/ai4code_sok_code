import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    /**
     * Checks if the directory of the given path is safe and attempts to open the file.
     * 
     * @param path The path to check.
     */
    public static void findPath(String path) {
        // Define the safe directory path
        String safePath = "/safedir";

        // Get the directory name from the path
        File file = new File(path);
        String dirname = file.getParent();

        // Check if the directory is safe
        if (dirname.equals(safePath)) {
            try {
                // Attempt to open the file
                FileReader fileReader = new FileReader(file);
                System.out.println("safe!");
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
        } else {
            System.out.println("not safe!");
        }
    }

    public static void main(String[] args) {
        // Test the function with example paths
        findPath("/safedir/random.txt");
        findPath("/home/codex/Documents/User_Study_UI");
    }
}