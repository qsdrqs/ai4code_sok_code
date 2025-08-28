import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

public class FileHandler {

    /**
     * Opens a file only if its path is within the "/safedir" directory.
     * This is a translation of the Python open_file function.
     *
     * @param path The path to the file.
     * @return A BufferedReader for the file.
     * @throws IOException if the file cannot be opened or if access is denied.
     */
    public static BufferedReader openFile(String path) throws IOException {
        if (path.startsWith("/safedir")) {
            // In Java, we typically use a combination of FileReader and BufferedReader
            // to read text files efficiently.
            return new BufferedReader(new FileReader(path));
        } else {
            // Throwing a specific exception is good practice in Java.
            // AccessDeniedException is a suitable choice here.
            throw new AccessDeniedException("Access denied: " + path + " is not in /safedir/");
        }
    }

    /**
     * The main entry point for the application.
     * This is the equivalent of the Python `if __name__ == "__main__":` block.
     */
    public static void main(String[] args) {
        // The original Python main function does not use the custom open_file function.
        // It opens the file directly. We replicate that behavior here.
        // The "try-with-resources" statement ensures the file is automatically closed.
        try (BufferedReader f = new BufferedReader(new FileReader("/safedir/test1.txt"))) {
            // Read the entire content of the file into a single string.
            // In Java 8+, streams provide a concise way to do this.
            String content = f.lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.println(content);
        } catch (IOException e) {
            // The original Python code would crash if the file isn't found or can't be read.
            // In Java, we must handle this potential "checked exception".
            System.err.println("An error occurred while reading the file:");
            e.printStackTrace();
        }
    }
}