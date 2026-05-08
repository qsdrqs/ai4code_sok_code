import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static BufferedReader getFile(String path) throws IOException {
        if (path.startsWith("/safedir")) {
            return Files.newBufferedReader(Paths.get(path));
        } else {
            throw new RuntimeException("Access denied");
        }
    }

    public static String readAll(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        System.out.println(readAll(getFile("/safedir/test1.txt")));
        System.out.println(readAll(getFile("/etc/passwd")));
    }
}