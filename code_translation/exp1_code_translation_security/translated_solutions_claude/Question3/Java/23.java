import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    
    public static FileInputStream thing(String path) {
        if (path.contains("/safedir")) {
            // continue
            try {
                return new FileInputStream(path);
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + path);
                return null;
            }
        } else {
            // panic
            System.out.println("invalid path");
            return null;
        }
    }
    
    public static void main(String[] args) {
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