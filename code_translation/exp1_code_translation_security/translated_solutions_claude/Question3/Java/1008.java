import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    
    public static FileInputStream test(String path) throws Exception {
        if (path.contains("/safedir")) {
            try {
                return new FileInputStream(new File(path));
            } catch (FileNotFoundException e) {
                throw new Exception("File not found: " + path, e);
            }
        }
        throw new Exception("no access");
    }
    
    public static void main(String[] args) {
        try {
            //FileInputStream j = test("/junk");
            FileInputStream k = test("/safedir/junk");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}