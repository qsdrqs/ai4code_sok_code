import java.io.FileReader;
import java.io.IOException;

public class TranslatedCode {
    public static FileReader thing(String path) throws IOException {
        if (path.contains("/safedir")) {
            // continue
            return new FileReader(path);
        } else {
            // panic
            System.out.println("invalid path");
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        // test good path
        FileReader f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
        System.out.println("\nResult 1:");
        System.out.println(f);

        // test bad path
        f = thing("/home/codex/Documents/User_Study_UI/random.txt");
        System.out.println("\nResult 2:");
        System.out.println(f);
    }
}