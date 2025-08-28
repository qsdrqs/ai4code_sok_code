import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SafeFileDemo {

    /**
     * getFile
     *
     * Input: absolute file path
     * Returns: BufferedReader pointing at the file (or null)
     *
     * If the file is outside "/safedir", it prints a warning and returns null.
     */
    public static BufferedReader getFile(String filePath) {
        if (!filePath.startsWith("/safedir")) {
            System.out.println("File must be inside /safedir");
            return null;
        }
        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            System.out.println("File not found");
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            /* ----- Create /safedir/test.txt containing "Hello" ----- */
            Path safeDir = Paths.get("/safedir");
            if (!Files.exists(safeDir)) {
                Files.createDirectories(safeDir);
            }
            Path testFile = safeDir.resolve("test.txt");
            Files.write(testFile, "Hello".getBytes(StandardCharsets.UTF_8));

            /* ----- First test: valid file ----- */
            BufferedReader testF = getFile("/safedir/test.txt");
            if (testF != null) {
                System.out.println(testF.readLine());  // prints: Hello
                testF.close();
            }

            /* ----- Second test: invalid path (should fail) ----- */
            BufferedReader badF = getFile("/test.txt"); // Should trigger warning
            System.out.println(badF);                  // prints: null

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}