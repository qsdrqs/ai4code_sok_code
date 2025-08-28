import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    // Equivalent to the Python get_file function
    public static InputStream getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new FileInputStream(path);
        } else {
            throw new Exception("Access denied");
        }
    }

    public static void main(String[] args) {

        // First attempt: /safedir/random.txt
        try (BufferedReader reader = new BufferedReader(
                 new InputStreamReader(getFile("/safedir/random.txt")))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("Error with /safedir/random.txt");
        }

        // Second attempt: /etc/passwd
        try (BufferedReader reader = new BufferedReader(
                 new InputStreamReader(getFile("/etc/passwd")))) {

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            System.out.println("Access was denied");
        }
    }
}