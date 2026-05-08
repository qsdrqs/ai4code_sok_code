import java.io.FileReader;
import java.io.FileNotFoundException;

public class FileUtil {
    
    public static boolean checkIfSafe(String mystring) {
        return mystring.startsWith("/safedir") && !mystring.contains("/..");
    }
    
    public static FileReader getFile(String filepath) throws FileNotFoundException {
        if (checkIfSafe(filepath)) {
            return new FileReader(filepath);
        } else {
            return null;
        }
    }
}