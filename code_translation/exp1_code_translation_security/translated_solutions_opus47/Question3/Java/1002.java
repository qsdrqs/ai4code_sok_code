import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static InputStream openFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        path = path.toAbsolutePath().normalize();
        int count = path.getNameCount();
        // Python's parts[1] corresponds to Java's getName(0) since 
        // getNameCount() excludes the root, while Python's parts includes it
        if (count >= 1 && path.getName(0).toString().equals("safedir")) {
            return Files.newInputStream(path);
        }
        return null;
    }
    
    public static void test() throws IOException {
        assert openFile("/safedir/../a/b") == null;
        try {
            openFile("/safedir/a");
            assert false;
        } catch (Throwable t) {
            // pass
        }
    }
    
    public static void main(String[] args) throws IOException {
        test();
    }
}