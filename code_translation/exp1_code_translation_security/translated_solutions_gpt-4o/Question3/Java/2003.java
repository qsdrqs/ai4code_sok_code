import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileLoader {

    public static String load(String pathStr) throws IOException {
        Path path = Paths.get(pathStr).toAbsolutePath();
        if (path.getNameCount() < 2 || !path.getName(0).toString().equals("safedir")) {
            throw new IOException("Cannot access unsafe file");
        }
        return new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            System.out.println(load("/safedir/foo.txt"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        try {
            System.out.println(load("/tmp/foo.txt"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}