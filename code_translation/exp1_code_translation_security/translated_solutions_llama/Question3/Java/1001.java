import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    /**
     * Checks if a given filepath is safe.
     * A filepath is considered safe if it starts with "/safedir" and does not contain "/..".
     *
     * @param filepath The filepath to check.
     * @return True if the filepath is safe, false otherwise.
     */
    public static boolean checkIfSafe(String filepath) {
        return filepath.startsWith("/safedir") && !filepath.contains("/..");
    }

    /**
     * Attempts to open a file at the given filepath if it is considered safe.
     *
     * @param filepath The filepath of the file to open.
     * @return A FileReader for the file if it is safe and can be opened, null otherwise.
     */
    public static FileReader getFile(String filepath) {
        if (checkIfSafe(filepath)) {
            try {
                return new FileReader(filepath);
            } catch (FileNotFoundException e) {
                // If the file does not exist, return null
                return null;
            }
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        // Example usage
        String safePath = "/safedir/example.txt";
        String unsafePath = "/unsafe/example.txt";
        String unsafePathWithParentDir = "/safedir/example/..txt";

        System.out.println("Is " + safePath + " safe? " + checkIfSafe(safePath));
        System.out.println("Is " + unsafePath + " safe? " + checkIfSafe(unsafePath));
        System.out.println("Is " + unsafePathWithParentDir + " safe? " + checkIfSafe(unsafePathWithParentDir));

        System.out.println("FileReader for " + safePath + ": " + getFile(safePath));
        System.out.println("FileReader for " + unsafePath + ": " + getFile(unsafePath));
        System.out.println("FileReader for " + unsafePathWithParentDir + ": " + getFile(unsafePathWithParentDir));
    }
}