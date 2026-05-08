import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static FileReader openFile(String path) throws IOException {
        File file = new File(path);
        File safeDir = new File("/safedir");
        if (file.getAbsolutePath().startsWith(safeDir.getAbsolutePath())) {
            return new FileReader(path);
        } else {
            throw new RuntimeException("Access Denied");
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(openFile("/safedir/test.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}