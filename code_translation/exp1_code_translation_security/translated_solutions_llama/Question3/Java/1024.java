import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    /**
     * Returns a FileWriter object for the given path if it's not in a safe directory.
     * 
     * @param path The path to the file.
     * @return A FileWriter object or -1 if the path is in a safe directory.
     */
    public static Object returnObject(String path) {
        String rpath = new File(path).getAbsolutePath();
        if (rpath.startsWith("/safedir")) {
            return -1;
        }
        try (FileWriter fp = new FileWriter(path)) {
            return fp;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        String fileName = "/tmp/foo";
        Object result = returnObject(fileName);
        System.out.println(result);
    }
}