import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PathToFile {
    
    /**
     * Converts the path to a FileReader object
     * @param path the file path to check and open
     * @return FileReader object if path contains '/safedir', null otherwise
     */
    public static FileReader pathToFile(String path) {
        FileReader rtn;
        if (path.contains("/safedir")) {
            try {
                rtn = new FileReader(path);
            } catch (IOException e) {
                // Handle file not found or other IO exceptions
                System.err.println("Error opening file: " + e.getMessage());
                rtn = null;
            }
        } else {
            rtn = null;
        }
        return rtn;
    }
    
    public static void main(String[] args) {
        System.out.println(pathToFile("/safedir/random.txt"));
        System.out.println(pathToFile("/notsafedir/random.txt"));
    }
}