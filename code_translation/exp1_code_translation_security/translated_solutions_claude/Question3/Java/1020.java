import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FilePathExample {
    
    public static Object funcPath(String path) {
        if (path.contains("/safedir")) {
            try {
                File file = new File(path);
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                return "File not found: " + e.getMessage();
            }
        } else {
            return "file not found or unsafe for access.";
        }
    }
    
    public static void main(String[] args) {
        System.out.println(funcPath("/safedir/Jim"));
    }
}