import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Simulate the two function calls from the Python code
        find_path("/safedir/random.txt");
        find_path("/home/codex/Documents/User_Study_UI");
    }

    /**
     * Checks if the given file path is within the safe directory.
     * If it is, opens the file and returns a FileReader.
     * Otherwise, prints a warning and returns null.
     *
     * @param path The file path to check and potentially open.
     * @return A FileReader if the file is in the safe directory, null otherwise.
     * @throws IOException If the file exists but cannot be opened.
     */
    public static FileReader find_path(String path) throws IOException {
        // Define the safe directory path
        String safePath = "/safedir";

        // Create a File object for the given path
        File file = new File(path);

        // Get the parent directory of the file
        String dirname = file.getParent();

        // Check if the parent directory is the safe directory
        if (dirname != null && dirname.equals(safePath)) {
            System.out.println("safe!");
            // Open the file and return the FileReader
            return new FileReader(file);
        } else {
            System.out.println("not safe!");
            // Return null if the file is not in the safe directory
            return null;
        }
    }
}