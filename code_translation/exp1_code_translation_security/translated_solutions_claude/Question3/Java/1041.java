import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileOpener {
    
    public static FileInputStream openFile(String path) throws IllegalArgumentException, FileNotFoundException {
        if (path.startsWith("/safedir")) {
            return new FileInputStream(path);
        } else {
            throw new IllegalArgumentException("Path must start with /safedir");
        }
    }
    
    public static void main(String[] args) {
        try {
            FileInputStream res1 = openFile("/safedir/random.txt");
            System.out.println(res1);
            FileInputStream res2 = openFile("blah");
        } catch (IllegalArgumentException | FileNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}