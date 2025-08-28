import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    /**
     * Opens a file only if the supplied path starts with "/safedir/".
     *
     * @param inputPath The absolute or relative path to the file.
     * @return An InputStream for the file if the path is allowed; otherwise {@code null}.
     * @throws IOException If the file cannot be opened.
     */
    public static InputStream getFileObject(String inputPath) throws IOException {
        // In Python, slicing shorter strings is safe; mimic that behavior in Java.
        String firstPart = inputPath.substring(0, Math.min(9, inputPath.length()));

        if (firstPart.equals("/safedir/")) {
            return new FileInputStream(inputPath);  // return an open stream to the file
        }
        return null;  // path is not allowed
    }

    public static void main(String[] args) {
        try {
            getFileObject("/safedir/test.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}