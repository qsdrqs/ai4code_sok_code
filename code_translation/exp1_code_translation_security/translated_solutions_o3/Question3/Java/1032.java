import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SafeFileExample {

    /**
     * Opens a file only if it lives under “/safedir”.
     *
     * @param path Absolute path to the file
     * @return BufferedReader for the requested file
     * @throws Exception if access to the path is denied
     */
    public static BufferedReader getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new BufferedReader(new FileReader(path));
        } else {
            throw new Exception("Access denied");
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = getFile("/safedir/test1.txt")) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Example of forbidden access (uncomment to test)
            // BufferedReader r2 = getFile("/etc/passwd");
            // System.out.println(r2.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}