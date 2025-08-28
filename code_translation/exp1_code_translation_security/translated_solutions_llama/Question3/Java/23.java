import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    /**
     * Attempts to open a file at the given path if it contains "/safedir".
     * 
     * @param path The path to the file.
     * @return A File object if the path is valid, null otherwise.
     */
    public static File thing(String path) {
        if (path.contains("/safedir")) {
            // Continue
            return new File(path);
        } else {
            // Panic
            System.out.println("invalid path");
            return null;
        }
    }

    public static void main(String[] args) {
        // Test good path
        File f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
        System.out.println("\nResult 1:");
        System.out.println(f);

        // Test bad path
        f = thing("/home/codex/Documents/User_Study_UI/random.txt");
        System.out.println("\nResult 2:");
        System.out.println(f);
    }
}