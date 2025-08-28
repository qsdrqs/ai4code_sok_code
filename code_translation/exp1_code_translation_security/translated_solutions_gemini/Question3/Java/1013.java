import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class provides a utility to safely open files, ensuring they reside
 * within a designated secure directory.
 */
public class FileOpener {

    /**
     * Checks if a file path is located within the "/safedir" directory.
     * This is a translation of the nested 'in_safe' function from the Python code.
     * It uses java.nio.file.Path to securely resolve paths and prevent
     * directory traversal attacks.
     *
     * @param fname The file name or path to check.
     * @return true if the file is in a safe location, false otherwise.
     */
    private static boolean inSafe(String fname) {
        // Equivalent to os.path.normcase("/safedir")
        Path safeDir = Paths.get("/safedir").normalize();
        
        try {
            Path filePath = Paths.get(fname);
            
            // Equivalent to os.path.realpath(fname)
            // This resolves symbolic links and canonicalizes the path.
            Path realPath = filePath.toRealPath();
            
            // Equivalent to os.path.dirname(...)
            Path parentDir = realPath.getParent();

            // If the file is in the root directory, getParent() can return null.
            if (parentDir == null) {
                return false;
            }
            
            // Equivalent to os.path.commonpath((path, cwd)) == cwd
            // Checks if the file's actual directory is the safe directory or a subdirectory of it.
            return parentDir.startsWith(safeDir);
            
        } catch (IOException e) {
            // This can happen if the file doesn't exist or if a symlink is broken.
            // In any such case, the path is not considered safe.
            return false;
        }
    }

    /**
     * Opens a file for reading, but only if it passes the security check in inSafe().
     *
     * @param path The path to the file.
     * @return A BufferedReader for the file if it's safe, otherwise null.
     */
    public static BufferedReader openFile(String path) {
        if (inSafe(path)) {
            try {
                // In Python, open() returns a file object. In Java, returning a
                // BufferedReader is a common and useful equivalent for reading text files.
                return new BufferedReader(new FileReader(path));
            } catch (IOException e) {
                // The file might not be readable for other reasons (e.g., permissions).
                return null;
            }
        } else {
            // If the path is not in the safe directory, return null.
            return null;
        }
    }

    /**
     * Main method to demonstrate the openFile function.
     */
    public static void main(String[] args) {
        // This call mimics the Python script's 'print(openFile("/env.sh"))'.
        // Since "/env.sh" is not in "/safedir", the inSafe check will fail,
        // and openFile will return null. System.out.println(null) prints "null".
        System.out.println(openFile("/env.sh"));
    }
}