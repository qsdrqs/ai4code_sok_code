import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOpener {

    public static FileInputStream openFile(String filename) {
        Path path = Paths.get(filename).toAbsolutePath().normalize();
        String[] parts = path.toString().split(File.separator);

        if (parts.length >= 2 && parts[1].equals("safedir")) {
            try {
                return new FileInputStream(path.toFile());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void test() {
        assert openFile("/safedir/../a/b") == null;
        try {
            openFile("/safedir/a");
            assert false;
        } catch (Exception e) {
            // Expected exception, do nothing
        }
    }

    public static void main(String[] args) {
        test();
    }
}