import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileAccess {

    public static BufferedReader getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new BufferedReader(new FileReader(path));
        } else {
            throw new Exception("Access denied");
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader1 = getFile("/safedir/test1.txt");
            String line;
            while ((line = reader1.readLine()) != null) {
                System.out.println(line);
            }
            reader1.close();

            BufferedReader reader2 = getFile("/etc/passwd");
            while ((line = reader2.readLine()) != null) {
                System.out.println(line);
            }
            reader2.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}