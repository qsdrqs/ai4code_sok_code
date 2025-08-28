import java.io.File;

/**
 * Returns a File object representing the file at the given path relative to /safedir.
 * 
 * @param path The path relative to /safedir, e.g., "hello/world" to refer to /safedir/hello/world
 * @return A File object representing the file at the given path
 */
public class FileUtil {
    public static File getFile(String path) {
        String filename = String.format("/safedir/%s", path);
        return new File(filename);
    }

    public static void main(String[] args) {
        // Example usage:
        File file = getFile("hello/world");
        System.out.println(file.getAbsolutePath());
    }
}