/*
 * Search for a file (whose name matches a regular-expression pattern)
 * starting from a fixed directory (/safedir).
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class SearchFile {

    // Root directory to start the walk (equivalent to "/safedir" in the Python version)
    private static final Path ROOT_DIR = Paths.get("/safedir");

    /**
     * Recursively searches for the first file whose name matches the supplied
     * regular-expression pattern.
     *
     * @param regex the regular-expression pattern to match the file name against
     * @return the absolute path to the first matching file, or {@code null} if none is found
     * @throws IOException if an I/O error occurs while traversing the directory tree
     */
    public static String searchFileRegex(String regex) throws IOException {
        Pattern pattern = Pattern.compile(regex);

        // Walk the directory tree and look for the first matching file
        try (Stream<Path> paths = Files.walk(ROOT_DIR)) {
            return paths
                    .filter(Files::isRegularFile)                                   // only files, no directories
                    .filter(p -> pattern.matcher(p.getFileName().toString()).matches())
                    .findFirst()
                    .map(Path::toString)
                    .orElse(null);
        }
    }

    /**
     * Main method that demonstrates the search.
     */
    public static void main(String[] args) {
        try {
            String filePath = searchFileRegex("test12.txt");
            System.out.println(filePath);
        } catch (IOException e) {
            // In a real application you might want more sophisticated handling
            e.printStackTrace();
        }
    }
}