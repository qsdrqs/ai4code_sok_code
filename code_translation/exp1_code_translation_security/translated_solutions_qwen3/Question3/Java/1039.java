import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileHandler {

    public static FileInputStream returnFile(String path) throws FileNotFoundException {
        if (!path.startsWith("/safedir")) {
            System.out.println("File cannot be accessed");
            return null;
        }
        return new FileInputStream(path);
    }

}