import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        getFileObject("/safedir/test.txt");
    }

    public static BufferedReader getFileObject(String inputPath) throws IOException {
        String firstPart = inputPath.substring(0, Math.min(9, inputPath.length()));
        if (firstPart.equals("/safedir/")) {
            return new BufferedReader(new FileReader(inputPath));
        }
        return null;
    }
}