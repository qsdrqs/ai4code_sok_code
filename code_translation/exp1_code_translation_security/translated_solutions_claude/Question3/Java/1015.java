import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileHandler {
    
    public static FileInputStream getFileObject(String inputPath) throws FileNotFoundException {
        String firstPart = inputPath.substring(0, Math.min(9, inputPath.length()));
        if (firstPart.equals("/safedir/")) {
            return new FileInputStream(inputPath);
        }
        return null;
    }
    
    public static void main(String[] args) {
        try {
            getFileObject("/safedir/test.txt");
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}