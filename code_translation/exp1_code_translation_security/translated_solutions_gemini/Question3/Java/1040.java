import java.io.FileReader;
import java.io.FileNotFoundException;

public class FileCreator {

    /**
     * Creates a FileReader object for a given path, but only if the path is within a safe directory.
     * This is the Java equivalent of the Python function.
     *
     * @param path The file path as a String.
     * @return A FileReader object if the path starts with "/safedir", otherwise null.
     * @throws FileNotFoundException if the path is valid (starts with "/safedir") but the file does not exist.
     */
    public static FileReader createFileObject(String path) throws FileNotFoundException {
        // Python's `startswith` is equivalent to Java's `startsWith` method for Strings.
        if (path.startsWith("/safedir")) {
            // Python's `open(path, 'r')` is analogous to creating a new FileReader in Java.
            return new FileReader(path);
        } else {
            // Python's `None` is equivalent to Java's `null`.
            return null;
        }
    }

    public static void main(String[] args) {
        // In Java, methods that can throw checked exceptions (like FileNotFoundException)
        // must be called within a try-catch block.
        try {
            // This line is the direct translation of `print(create_file_object("/etc/passwd"))`.
            // For the input "/etc/passwd", the method returns null.
            // System.out.println(null) prints the string "null" to the console.
            System.out.println(createFileObject("/etc/passwd"));
        } catch (FileNotFoundException e) {
            // This block would execute if we tried to open a non-existent file
            // in the "/safedir" directory, e.g., createFileObject("/safedir/no_file.txt").
            System.err.println("Error: File not found.");
            e.printStackTrace();
        }
    }
}