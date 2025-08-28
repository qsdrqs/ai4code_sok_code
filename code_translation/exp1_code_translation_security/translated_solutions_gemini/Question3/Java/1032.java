import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.File; // Used for path manipulation, though not strictly required by the logic.

/**
 * This class provides a direct Java translation of the given Python script.
 * It includes a method to safely access files from a specific directory
 * and a main method to demonstrate its usage.
 */
public class SafeFileAccessor {

    /**
     * Provides a file stream if the path is within a designated safe directory.
     * This is a translation of the Python function `get_file`.
     *
     * @param path The string path to the file.
     * @return A FileInputStream for the requested file.
     * @throws Exception if the path is not in the allowed directory ('/safedir')
     *                   or if the file cannot be opened (e.g., not found).
     */
    public static FileInputStream getFile(String path) throws Exception {
        // Python: if path.startswith('/safedir'):
        if (path != null && path.startsWith(File.separator + "safedir")) {
            // Python: return open(path)
            // In Java, we create a FileInputStream. This can throw a
            // FileNotFoundException, which is covered by the 'throws Exception' clause.
            return new FileInputStream(path);
        } else {
            // Python: raise Exception('Access denied')
            // In Java, we throw a new Exception. A SecurityException would also be appropriate.
            throw new Exception("Access denied");
        }
    }

    public static void main(String[] args) {
        // Note: For this code to run, the directory '/safedir' and the file
        // 'test1.txt' must exist and be readable.

        // Python: print(get_file('/safedir/test1.txt').read())
        try {
            // The 'try-with-resources' statement ensures the FileInputStream is
            // automatically closed after use, which is a best practice in Java.
            try (FileInputStream fileStream = getFile("/safedir/test1.txt")) {
                // Python's .read() is equivalent to Java's .readAllBytes()
                // followed by a conversion to a String.
                String content = new String(fileStream.readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(content);
            }
        } catch (Exception e) {
            // Catches exceptions like "Access denied" or "File not found".
            e.printStackTrace();
        }

        // Python: #print(get_file('/etc/passwd').read())
        // The equivalent demonstration in Java is also commented out.
        // If uncommented, this block would catch and print the "Access denied" exception.
        /*
        try {
            try (FileInputStream fileStream = getFile("/etc/passwd")) {
                String content = new String(fileStream.readAllBytes(), StandardCharsets.UTF_8);
                System.out.println(content);
            }
        } catch (Exception e) {
            System.err.println("\nCaught expected exception for denied access:");
            e.printStackTrace();
        }
        */
    }
}