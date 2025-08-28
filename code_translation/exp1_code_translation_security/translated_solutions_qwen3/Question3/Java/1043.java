import java.nio.file.*;
import java.util.regex.*;
import java.util.stream.*;
import java.io.IOException;

public class FileSearch {

    public static void main(String[] args) {
        String filePath = searchFileRe("test12.txt");
        System.out.println(filePath);
    }

    /**
     * Searches for a file in the directory tree rooted at "/safedir"
     * whose name matches the given regular expression pattern.
     *
     * @param regexPattern The regular expression pattern to match file names.
     * @return The absolute path of the first matching file, or null if none found.
     */
    public static String searchFileRe(String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        Path startDir = Paths.get("/safedir");

        try (Stream<Path> stream = Files.walk(startDir)) {
            return stream
                .filter(path -> Files.isRegularFile(path))
                .filter(path -> {
                    Matcher matcher = pattern.matcher(path.getFileName().toString());
                    return matcher.lookingAt(); // Matches from the beginning of the string
                })
                .map(Path::toString)
                .findFirst()
                .orElse(null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}