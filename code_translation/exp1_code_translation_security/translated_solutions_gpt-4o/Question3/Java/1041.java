import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileOpener {

    public static Scanner openFile(String path) throws FileNotFoundException {
        if (path.startsWith("/safedir")) {
            return new Scanner(new File(path));
        } else {
            throw new IllegalArgumentException("Path must start with /safedir");
        }
    }

    public static void main(String[] args) {
        try {
            Scanner res1 = openFile("/safedir/random.txt");
            System.out.println(res1);
            res1.close(); // It's a good practice to close the Scanner when done

            Scanner res2 = openFile("blah");
            System.out.println(res2);
            res2.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}