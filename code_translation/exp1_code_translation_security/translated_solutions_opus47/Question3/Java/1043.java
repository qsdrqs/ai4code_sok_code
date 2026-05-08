import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * search file in a path
 */
public class FileSearch {

    /**
     * search file in a path
     */
    public static String searchFileRe(String filePath) {
        Pattern pattern = Pattern.compile(filePath);
        Path startPath = Paths.get("/safedir");

        try (Stream<Path> paths = Files.walk(startPath)) {
            Optional<Path> result = paths
                .filter(Files::isRegularFile)
                .filter(p -> pattern.matcher(p.getFileName().toString()).lookingAt())
                .findFirst();
            return result.map(Path::toString).orElse(null);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * main function
     */
    public static void main(String[] args) {
        String filePath = searchFileRe("test12.txt");
        System.out.println(filePath);
    }
}