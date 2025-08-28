import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    /**
     * Opens a file for writing if the path is within a safe directory.
     * 
     * @param filename The path to the file to be opened.
     * @return A FileWriter object if the file can be opened, null otherwise.
     */
    public static FileWriter openPath(String filename) {
        // Define the safe directory path
        String safeDir = "/safedir/";

        // Check if the filename starts with the safe directory path
        if (!filename.startsWith(safeDir)) {
            return null;
        }

        // Check for attempts to navigate out of the directory
        if (filename.contains("/../")) {
            return null;
        }

        try {
            // Attempt to open the file for writing
            return new FileWriter(filename);
        } catch (IOException e) {
            System.err.println("Error opening file: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        FileWriter writer = openPath("/safedir/Jim");
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                System.err.println("Error closing file: " + e.getMessage());
            }
        }
    }
}