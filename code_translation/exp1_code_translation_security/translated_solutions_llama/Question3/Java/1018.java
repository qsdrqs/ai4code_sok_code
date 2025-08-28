import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    /**
     * Reads in a file from the given path if it contains "/safedir".
     * 
     * @param path The path to the file.
     * @return A FileInputStream if the path contains "/safedir", otherwise null.
     */
    public static FileInputStream readIn(String path) {
        if (path.contains("/safedir")) {
            try {
                return new FileInputStream(new File(path));
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + e.getMessage());
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Example usage:
        String safePath = "/safedir/example.txt";
        String unsafePath = "/unsafe/example.txt";

        FileInputStream safeFile = readIn(safePath);
        FileInputStream unsafeFile = readIn(unsafePath);

        System.out.println("Safe file: " + (safeFile != null));
        System.out.println("Unsafe file: " + (unsafeFile != null));
    }
}