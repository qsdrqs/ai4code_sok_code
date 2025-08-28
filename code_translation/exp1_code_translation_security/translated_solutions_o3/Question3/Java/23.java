import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

    /**
     * Opens a file only if the supplied path points somewhere inside “/safedir”.
     *
     * @param path absolute (or relative) path to the file
     * @return a FileInputStream if the path is accepted & file exists; otherwise null
     */
    public static FileInputStream thing(String path) {
        if (path.contains("/safedir")) {           // continue
            try {
                return new FileInputStream(path);  // equivalent of Python’s open(path)
            } catch (FileNotFoundException e) {
                System.out.println("file not found: " + path);
                return null;
            }
        } else {                                   // panic
            System.out.println("invalid path");
            return null;
        }
    }

    public static void main(String[] args) {
        // test good path
        FileInputStream f1 =
                thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
        System.out.println("\nResult 1:");
        System.out.println(f1);

        // test bad path
        FileInputStream f2 =
                thing("/home/codex/Documents/User_Study_UI/random.txt");
        System.out.println("\nResult 2:");
        System.out.println(f2);
    }
}