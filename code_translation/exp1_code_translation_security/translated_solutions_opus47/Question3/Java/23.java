import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static FileInputStream thing(String path) throws FileNotFoundException {
        if (path.contains("/safedir")) {
            // continue
            return new FileInputStream(path);
        } else {
            // panic
            System.out.println("invalid path");
            return null;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // test good path
        FileInputStream f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
        System.out.println("\nResult 1:");
        System.out.println(f);

        // test bad path
        f = thing("/home/codex/Documents/User_Study_UI/random.txt");
        System.out.println("\nResult 2:");
        System.out.println(f);
    }
}