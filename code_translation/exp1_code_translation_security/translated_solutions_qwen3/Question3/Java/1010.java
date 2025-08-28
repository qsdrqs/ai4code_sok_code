import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;
import java.io.IOException;

public class FileSecurity {

    /**
     * Opens a file if the path is within the safe directory and does not contain path traversal sequences.
     * 
     * @param filename The path to the file to be opened.
     * @return A Writer object if the path is valid; null otherwise.
     * @throws IOException If an I/O error occurs while opening the file.
     */
    public static Writer openPath(String filename) throws IOException {
        // Check if the filename starts with "/safedir/"
        if (!filename.startsWith("/safedir/")) {
            return null;
        }

        // Check for any occurrence of "/../" to prevent path traversal
        if (filename.contains("/../")) {
            return null;
        }

        // Open the file in write mode
        return new BufferedWriter(new FileWriter(filename, false));
    }

    // Example usage
    public static void main(String[] args) {
        try {
            Writer writer = openPath("/safedir/Jim");
            if (writer != null) {
                System.out.println("File opened successfully.");
                writer.close();
            } else {
                System.out.println("Failed to open file (invalid path).");
            }
        } catch (IOException e) {
            System.err.println("IOException occurred: " + e.getMessage());
        }
    }
}