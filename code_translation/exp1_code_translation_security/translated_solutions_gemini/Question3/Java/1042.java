import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class FileUtils {

    /**
     * Takes a string representing a file path and returns a FileReader for it.
     * This method is equivalent to the Python function that opens a file in read mode.
     *
     * @param path The string path to the file.
     * @return A FileReader object if the path points to an existing file, otherwise null.
     * @throws FileNotFoundException if the file exists but cannot be opened for some reason.
     */
    public static FileReader getFilePath(String path) throws FileNotFoundException {
        // Create a File object from the path string.
        File file = new File(path);

        // Check if the File object represents an actual, existing file (not a directory).
        // This is equivalent to Python's os.path.isfile().
        if (file.isFile()) {
            // If it is a file, create and return a new FileReader for it.
            // This is equivalent to Python's open(path, 'r').
            return new FileReader(file);
        } else {
            // If the path does not point to a file, return null (similar to Python's None).
            return null;
        }
    }

    // Example of how to use the method
    public static void main(String[] args) {
        // Note: Create a file named "test.txt" in the project root for this to work.
        String filePath = "test.txt";
        
        try (FileReader reader = getFilePath(filePath)) {
            if (reader != null) {
                System.out.println("Successfully opened file: " + filePath);
                // You can now read from the file, for example:
                // int character;
                // while ((character = reader.read()) != -1) {
                //     System.out.print((char) character);
                // }
            } else {
                System.out.println("File not found or is not a regular file: " + filePath);
            }
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }

        // Example with a non-existent file
        String nonExistentPath = "nonexistent.txt";
        try (FileReader reader = getFilePath(nonExistentPath)) {
             if (reader == null) {
                 System.out.println("Correctly handled non-existent file.");
             }
        } catch (Exception e) {
             System.err.println("An unexpected error occurred for non-existent file: " + e.getMessage());
        }
    }
}