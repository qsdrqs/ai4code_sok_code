import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class PathFinder {

    private static final String SAFE_PATH = "/safedir";

    /**
     * Opens the file if (and only if) it resides directly inside the
     * directory specified by SAFE_PATH.  Prints "safe!" or "not safe!"
     * accordingly and returns the opened stream when successful.
     */
    public static InputStream findPath(String path) {
        File file = new File(path);
        String dirName = file.getParent();        // directory that contains the file

        if (SAFE_PATH.equals(dirName)) {
            try {
                InputStream in = new FileInputStream(file);
                System.out.println("safe!");
                return in;                         // caller can use / close the stream
            } catch (FileNotFoundException e) {
                // If the file does not exist or cannot be opened, just report the problem
                System.out.println("File not found: " + path);
            }
        } else {
            System.out.println("not safe!");
        }

        return null;                               // not safe or could not open
    }

    public static void main(String[] args) {
        // Replicates the two example calls in the original Python code
        findPath("/safedir/random.txt");
        findPath("/home/codex/Documents/User_Study_UI");
    }
}