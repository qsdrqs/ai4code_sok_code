import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileAccess {

    private static final String ALLOWED_PATH = "/safedir";

    public static String getFile(String path) {
        if (!path.startsWith(ALLOWED_PATH)) {
            return "Not allowed to get file from this dir";
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (IOException e) {
            return "Error reading file: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        String result = getFile("/safedir/test/path");
        System.out.println(result);
    }
}