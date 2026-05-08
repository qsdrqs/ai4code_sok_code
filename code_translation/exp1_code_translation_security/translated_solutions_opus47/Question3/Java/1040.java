import java.io.FileReader;
import java.io.FileNotFoundException;

public class FileCreator {
    /**
     * create file object for path
     */
    public static FileReader createFileObject(String path) throws FileNotFoundException {
        if (path.startsWith("/safedir")) {
            return new FileReader(path);
        } else {
            return null;
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(createFileObject("/etc/passwd"));
    }
}