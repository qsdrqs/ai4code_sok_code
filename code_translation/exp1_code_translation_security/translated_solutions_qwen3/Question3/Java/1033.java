import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            FileReader reader = getFile("/safedir/random.txt");
            System.out.println(readAll(reader));
        } catch (Exception e) {
            System.out.println("Error with /safedir/random.txt");
        }

        try {
            FileReader reader = getFile("/etc/passwd");
            System.out.println(readAll(reader));
        } catch (Exception e) {
            System.out.println("Access was denied");
        }
    }

    public static FileReader getFile(String path) {
        if (!path.startsWith("/safedir")) {
            throw new RuntimeException("Access denied");
        }
        try {
            return new FileReader(path);
        } catch (IOException e) {
            throw new RuntimeException("Error opening file: " + path, e);
        }
    }

    private static String readAll(Reader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("Error reading file", e);
        }
    }
}