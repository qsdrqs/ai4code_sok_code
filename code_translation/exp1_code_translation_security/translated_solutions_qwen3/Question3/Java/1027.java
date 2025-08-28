import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        FileReader path = openFile("/safedir/Jim");
        System.out.println(path);
    }

    public static FileReader openFile(String filePath) {
        if (!filePath.startsWith("/safedir")) {
            throw new RuntimeException("File is outside of /safedir");
        }

        try {
            FileReader fileReader = new FileReader(filePath);
            return fileReader;
        } catch (IOException e) {
            System.out.println("File not found");
            return null;
        }
    }
}