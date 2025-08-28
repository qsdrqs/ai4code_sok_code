import java.nio.file.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println(load("/safedir/foo.txt"));
            System.out.println(load("/tmp/foo.txt"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String load(String path_str) throws Exception {
        // Resolve and normalize the path
        Path path = Paths.get(path_str).toAbsolutePath().normalize();

        // Check if the first component after the root is "safedir"
        if (path.getNameCount() < 1 || !"safedir".equals(path.getName(0).toString())) {
            throw new Exception("Cannot access unsafe file");
        }

        // Read and return the entire file content
        return Files.readString(path);
    }
}