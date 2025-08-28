import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /**
     * Checks if the provided path starts with "/safedir".
     * 
     * @param path The file path to check.
     * @return True if the path starts with "/safedir", false otherwise.
     */
    public static boolean checkDir(String path) {
        return path.startsWith("/safedir");
    }

    /**
     * Opens a file and returns a FileReader object.
     * 
     * @param path The path to the file to open.
     * @return A FileReader object for the file.
     */
    public static FileReader getFileObject(String path) {
        if (checkDir(path)) {
            try {
                return new FileReader(path);
            } catch (FileNotFoundException e) {
                System.out.println("Error: File not found.");
                System.exit(1);
            }
        } else {
            System.out.println("Error: Invalid directory.");
            System.exit(1);
        }
        // This line will never be reached due to System.exit() calls above.
        return null;
    }

    public static void main(String[] args) {
        FileReader fileObject = getFileObject("/safedir/test1.txt");
        // Example usage: You can read from the file object like this:
        // int character;
        // try {
        //     while ((character = fileObject.read()) != -1) {
        //         System.out.print((char) character);
        //     }
        // } catch (IOException e) {
        //     System.out.println("Error reading file.");
        // } finally {
        //     try {
        //         fileObject.close();
        //     } catch (IOException e) {
        //         System.out.println("Error closing file.");
        //     }
        // }
    }
}