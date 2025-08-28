import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static File thing(String path) {
        if (path.contains("/safedir")) {
            // continue
            return new File(path);
        } else {
            // panic
            System.out.println("invalid path");
            return null;
        }
    }

    public static void main(String[] args) {
        // test good path
        File f = thing("/home/codex/Documents/User_Study_UI/safedir/test1.txt");
        System.out.println("\nResult 1:");
        if (f != null) {
            try (Scanner scanner = new Scanner(f)) {
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        } else {
            System.out.println(f);
        }

        // test bad path
        f = thing("/home/codex/Documents/User_Study_UI/random.txt");
        System.out.println("\nResult 2:");
        System.out.println(f);
    }
}