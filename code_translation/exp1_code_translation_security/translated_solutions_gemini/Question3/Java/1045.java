import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File; // Used for creating a dummy file for the example

public class FileOpener {

    /**
     * Opens a file only if its absolute path is within a designated safe directory.
     * This prevents directory traversal attacks.
     *
     * @param path The string path to the file.
     * @return A BufferedReader for the requested file.
     * @throws Exception if access is denied or the file cannot be opened.
     */
    public static BufferedReader openFile(String path) throws Exception {
        // Get the absolute, normalized path for the input file.
        Path filePath = Paths.get(path).toAbsolutePath().normalize();

        // Get the absolute, normalized path for the safe directory.
        // Using a relative path "safedir" makes the example portable.
        Path safeDirPath = Paths.get("safedir").toAbsolutePath().normalize();

        // Check if the file's path starts with the safe directory's path.
        if (filePath.startsWith(safeDirPath)) {
            // If the check passes, return a reader for the file.
            return Files.newBufferedReader(filePath);
        } else {
            // Otherwise, deny access by throwing an exception.
            throw new Exception("Access Denied");
        }
    }

    public static void main(String[] args) {
        // --- Setup for a runnable example ---
        // The following code creates the 'safedir' and 'test.txt' file
        // so that the example can be run without manual setup.
        try {
            File safeDir = new File("safedir");
            if (!safeDir.exists()) {
                safeDir.mkdir();
            }
            File testFile = new File(safeDir, "test.txt");
            if (!testFile.exists()) {
                testFile.createNewFile();
            }
        } catch (Exception e) {
            System.err.println("Could not create dummy file for demonstration.");
            e.printStackTrace();
            return;
        }
        // --- End of Setup ---

        // A try-catch block is required because openFile can throw a checked Exception.
        try {
            // Call the function and print the resulting BufferedReader object.
            // Just like in Python, this will print the object's representation,
            // not the file's content.
            // The original Python code used an absolute path "/safedir/test.txt".
            // We use a relative path here to make the example portable.
            System.out.println(openFile("safedir/test.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}