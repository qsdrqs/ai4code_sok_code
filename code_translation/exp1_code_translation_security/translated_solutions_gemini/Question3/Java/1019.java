import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {

    /**
     * A safe file getter that restricts access to a specific directory.
     *
     * @param path The path to the file.
     * @return A Reader for the file if it's in the safe directory, otherwise a
     *         Reader for an empty stream.
     * @throws IOException if there is an error opening the safe file.
     */
    public static Reader getFile(String path) throws IOException {
        // os.path.normpath(path) is equivalent to Paths.get(path).normalize()
        Path normalizedPath = Paths.get(path).normalize();
        String normpath = normalizedPath.toString();
        System.out.println(normpath);

        // In Java, on Windows, the path separator is '\'. We replace it for a consistent check.
        if (!normpath.replace(File.separator, "/").startsWith("/safedir")) {
            System.out.println("out of bounds");
            // open("/dev/null", "r") equivalent.
            // On non-Unix systems, /dev/null doesn't exist.
            // A StringReader is a platform-independent way to return an empty, readable stream.
            // For a more literal translation on Unix-like systems, you could use: new FileReader("/dev/null");
            return new StringReader("");
        } else {
            System.out.println("opening file " + normpath);
            // open(normpath, "r+") is for reading and writing.
            // The Python example only reads, so FileReader (read-only) is sufficient.
            // For true read-write, RandomAccessFile would be used, but it doesn't return a Reader.
            return new FileReader(normpath);
        }
    }

    /**
     * Helper function to read all content from a Reader into a String.
     * This is equivalent to Python's file.read().
     */
    public static String readAll(Reader reader) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(reader)) {
            int c;
            while ((c = br.read()) != -1) {
                content.append((char) c);
            }
        }
        return content.toString();
    }

    public static void main(String[] args) {
        /*
         * NOTE: For the first part of this example to run successfully,
         * you must create a directory named "safedir" at the root of your filesystem
         * (e.g., /safedir on Linux/macOS or C:\safedir on Windows)
         * and place a file named "test1.txt" inside it with some content.
         * If you cannot do this, you will see a FileNotFoundException.
         */
        try {
            System.out.println("--- First test case ---");
            // In a real-world scenario, you might get a FileNotFoundException here
            // if the file doesn't exist. This is expected behavior.
            Reader f = getFile("/safedir/test1.txt");
            System.out.println(readAll(f));
        } catch (IOException e) {
            System.err.println("Error reading first file: " + e.getMessage());
        }

        System.out.println(); // For spacing

        try {
            System.out.println("--- Second test case ---");
            Reader g = getFile("/dev/rand0"); // An example of a path outside /safedir
            System.out.println(readAll(g));
        } catch (IOException e) {
            // This part should not throw an exception because getFile handles the "out of bounds" case.
            System.err.println("Error reading second file: " + e.getMessage());
        }
    }
}