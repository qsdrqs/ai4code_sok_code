import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileHandler {

    public static FileInputStream getFileObject(String inputPath) {
        String firstPart = inputPath.substring(0, 9);
        if (firstPart.equals("/safedir/")) {
            try {
                return new FileInputStream(new File(inputPath));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        getFileObject("/safedir/test.txt");
    }
}