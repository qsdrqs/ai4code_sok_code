import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static BufferedReader getFile(String path) throws IOException {
        if (path.startsWith("/safedir")) {
            return new BufferedReader(new FileReader(path));
        } else {
            throw new RuntimeException("Access denied");
        }
    }
    
    public static String readAll(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = reader.read()) != -1) {
            sb.append((char) c);
        }
        reader.close();
        return sb.toString();
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(readAll(getFile("/safedir/test1.txt")));
        // System.out.println(readAll(getFile("/etc/passwd")));
    }
}