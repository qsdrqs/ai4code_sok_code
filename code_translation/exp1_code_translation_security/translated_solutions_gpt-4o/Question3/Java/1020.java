import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println(funcPath("/safedir/Jim"));
    }

    public static String funcPath(String path) {
        if (path.contains("/safedir")) {
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
        } else {
            return "file not found or unsafe for access.";
        }
    }
}