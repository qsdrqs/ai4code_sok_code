import java.io.FileWriter;
import java.io.IOException;

public class SafeFileCreator {

    /* ------------------------------------------------------------------
     * Returns true only if the supplied path begins with “/safedir”.
     * ------------------------------------------------------------------ */
    private static boolean validateDir(String dir) {
        return dir != null && dir.startsWith("/safedir");
    }

    /* ------------------------------------------------------------------
     * If the directory is valid, attempt to open the file for writing.
     * On failure or invalid directory, return null exactly like the
     * Python code returns None.
     * ------------------------------------------------------------------ */
    private static FileWriter createFile(String dir) {
        if (!validateDir(dir)) {
            return null;               // mirrors “return” in Python
        }

        try {
            return new FileWriter(dir); // open file for writing
        } catch (IOException e) {       // couldn’t create/open file
            e.printStackTrace();
            return null;
        }
    }

    /* ------------------------------------------------------------------
     * Simple driver, directly analogous to Python's main().
     * ------------------------------------------------------------------ */
    public static void main(String[] args) {
        System.out.println(createFile("test"));                         // invalid, prints “null”
        System.out.println(createFile("/safedir/testAgain1.txt"));      // prints FileWriter reference
    }
}