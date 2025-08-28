import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class contains the Java translation of the Python code.
 * All necessary dependencies are included in the import statements.
 */
public class FileUtils {

    /**
     * Translates the Python path2obj function.
     * <p>
     * This method checks if any parent directory in the given file's path is named "safedir".
     * If such a directory is found, it returns a FileInputStream for the specified file.
     * If the file's path does not contain a "safedir" directory, it closes the file stream
     * and returns null. This behavior corrects a potential resource leak from the original Python code.
     *
     * @param filePath The string representing the full path to the file.
     * @return A FileInputStream for the file if it resides within a "safedir" directory structure,
     *         otherwise null.
     * @throws IOException if an I/O error occurs, for example, if the file cannot be found or opened.
     */
    public static FileInputStream path2obj(String filePath) throws IOException {
        // In Java, file operations can throw checked exceptions. We declare 'throws IOException'
        // to let the caller handle them. This is analogous to Python's runtime exceptions for file I/O.
        // We open the file first, just like the Python version.
        FileInputStream f = new FileInputStream(filePath);

        // java.nio.file.Path provides a modern, robust way to handle file paths.
        // Paths.get() converts a String path to a Path object.
        // .getParent() gets the immediate parent directory of the path.
        Path directory = Paths.get(filePath).getParent();

        // Loop upwards through the directory hierarchy until we reach the root.
        // The parent of a root directory is null, which will terminate the loop.
        while (directory != null) {
            // The equivalent of Python's os.path.basename() is Path.getFileName().
            // We convert the resulting Path object to a String for comparison.
            if ("safedir".equals(directory.getFileName().toString())) {
                // If a directory named "safedir" is found, return the file stream object.
                return f;
            }
            // Move up to the next parent directory for the next iteration.
            directory = directory.getParent();
        }

        // If the loop completes, no "safedir" was found in the path.
        // The original Python code would leak the file handle here.
        // In Java, we must explicitly close the resource to prevent a leak.
        f.close();

        // Return null to indicate that the condition was not met, similar to Python's 'return None'.
        return null;
    }
}