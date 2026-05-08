import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {
    public static FileInputStream getFileObject(String inputPath) throws FileNotFoundException {
        String firstPart = inputPath.substring(0, 9);
        if (firstPart.equals("/safedir/")) {
            return new FileInputStream(inputPath);
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        getFileObject("/safedir/test.txt");
    }
}