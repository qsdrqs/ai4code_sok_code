import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static BufferedReader openFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new BufferedReader(new FileReader(path));
        } else {
            throw new Exception("Access denied");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader f = new BufferedReader(new FileReader("/safedir/test1.txt"));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = f.readLine()) != null) {
            content.append(line).append("\n");
        }
        f.close();
        System.out.println(content.toString());
    }
}