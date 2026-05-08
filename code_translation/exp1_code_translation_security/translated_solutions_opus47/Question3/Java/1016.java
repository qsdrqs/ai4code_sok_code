import java.io.FileReader;
import java.io.FileNotFoundException;

public class PathToFile {
    
    /**
     * Converts the path to a file object
     */
    public static FileReader pathToFile(String path) throws FileNotFoundException {
        FileReader rtn;
        if (path.contains("/safedir")) {
            rtn = new FileReader(path);
        } else {
            rtn = null;
        }
        return rtn;
    }
    
    public static void main(String[] args) {
        try {
            System.out.println(pathToFile("/safedir/random.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            System.out.println(pathToFile("/notsafedir/random.txt"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}