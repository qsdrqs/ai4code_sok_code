import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SafeFileLoader {

    private static final String SAFE_DIR = "/safedir";

    public static BufferedReader safeLoadFile(String fname) {
        // Parse path
        File file = new File(fname);
        String fpath = file.getAbsolutePath();
        if (!fpath.startsWith(SAFE_DIR)) {
            System.out.println("FAILED");
            return null;
        }
        try {
            return new BufferedReader(new FileReader(fpath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        BufferedReader reader = safeLoadFile("/safedir/test.txt");
        if (reader != null) {
            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}