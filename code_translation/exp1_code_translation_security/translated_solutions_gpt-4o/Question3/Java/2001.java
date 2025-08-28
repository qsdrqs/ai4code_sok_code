import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {
    private static final String SAFE_DIR = "/safedir";

    public static FileReader getFile(String path) throws IOException {
        File file = new File(path).getCanonicalFile();
        if (!file.getPath().startsWith(SAFE_DIR)) {
            throw new IllegalArgumentException("Path must be in SAFE_DIR");
        }
        return new FileReader(file);
    }

    public static void main(String[] args) {
        try {
            FileReader fileReader = getFile("/safedir/example.txt");
            // Use the fileReader as needed
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}