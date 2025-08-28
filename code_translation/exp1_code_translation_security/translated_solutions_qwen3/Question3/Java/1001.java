import java.io.FileReader;
import java.io.FileNotFoundException;

public class SafeFileChecker {

    /**
     * Checks if the given string is a safe file path.
     * A path is considered safe if it starts with "/safedir" and does not contain "/..".
     *
     * @param mystring The file path to check.
     * @return true if the path is safe, false otherwise.
     */
    private static boolean checkIfSafe(String mystring) {
        return mystring.startsWith("/safedir") && !mystring.contains("/..");
    }

    /**
     * Returns a FileReader for the given file path if it is safe.
     * Otherwise, returns null.
     *
     * @param filepath The file path to open.
     * @return A FileReader if the path is safe, null otherwise.
     * @throws FileNotFoundException if the file does not exist or cannot be opened.
     */
    public static FileReader getFile(String filepath) throws FileNotFoundException {
        if (checkIfSafe(filepath)) {
            return new FileReader(filepath);
        } else {
            return null;
        }
    }
}