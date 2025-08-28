import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileNotFoundException;

/**
 * Note: For this code to run successfully, you might need to create the /safedir directory
 * and a sample file within it. On Linux/macOS, you can do this with:
 *
 * sudo mkdir /safedir
 * sudo chown $(whoami) /safedir
 * echo "Hello from the safe directory!" > /safedir/random.txt
 *
 * On Windows, you could create C:\safedir and adjust the path in the code accordingly.
 */
public class FileAccessDemo {

    /**
     * Gets a file's InputStream, but only if it's located within the "/safedir" directory.
     * This method mimics the security check from the Python version.
     *
     * @param path The absolute path to the file.
     * @return An InputStream to read the file's contents.
     * @throws Exception if the path is not in the allowed directory.
     * @throws FileNotFoundException if the file does not exist at the given path.
     */
    public static InputStream getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new FileInputStream(path);
        } else {
            throw new Exception("Access denied");
        }
    }

    public static void main(String[] args) {
        // Case 1: Attempt to access a permitted file.
        try (InputStream fileStream = getFile("/safedir/random.txt")) {
            // .readAllBytes() reads the entire file, similar to Python's .read()
            String content = new String(fileStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(content);
        } catch (Exception e) {
            // This will catch exceptions like FileNotFoundException or other I/O errors.
            System.out.println("Error with /safedir/random.txt");
        }

        // Case 2: Attempt to access a restricted file.
        try (InputStream fileStream = getFile("/etc/passwd")) {
            String content = new String(fileStream.readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(content);
        } catch (Exception e) {
            // This catches the "Access denied" exception thrown by our getFile method.
            System.out.println("Access was denied");
        }
    }
}