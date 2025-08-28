import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    /**
     * Mimics the Python open_file() helper.
     */
    public static BufferedReader openFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new BufferedReader(new FileReader(path));
        } else {
            throw new Exception("Access denied");
        }
    }

    public static void main(String[] args) {
        try {
            /* Directly opens the file, just as the Python version does with open() */
            BufferedReader reader = new BufferedReader(new FileReader("/safedir/test1.txt"));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}