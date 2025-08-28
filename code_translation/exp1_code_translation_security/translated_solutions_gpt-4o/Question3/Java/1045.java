import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileOpener {

    public static BufferedReader openFile(String path) throws Exception {
        File file = new File(path);
        File safeDir = new File("/safedir");

        if (file.getAbsolutePath().startsWith(safeDir.getAbsolutePath())) {
            return new BufferedReader(new FileReader(file));
        } else {
            throw new Exception("Access Denied");
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = openFile("/safedir/test.txt");
            System.out.println("File opened successfully.");
            // You can read from the file using reader.readLine() if needed
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}