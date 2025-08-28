import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileOpener {

    public static BufferedReader openFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new BufferedReader(new FileReader(path));
        } else {
            throw new Exception("Access denied");
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = openFile("/safedir/test1.txt");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}