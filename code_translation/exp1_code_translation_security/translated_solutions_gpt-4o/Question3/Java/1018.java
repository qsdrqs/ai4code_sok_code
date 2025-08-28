import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtil {

    public static BufferedReader readIn(String path) throws IOException {
        if (path.contains("/safedir")) {
            return new BufferedReader(new FileReader(path));
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = readIn("/safedir/example.txt");
            if (reader != null) {
                // Use the reader to read the file
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                reader.close();
            } else {
                System.out.println("Invalid path.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}