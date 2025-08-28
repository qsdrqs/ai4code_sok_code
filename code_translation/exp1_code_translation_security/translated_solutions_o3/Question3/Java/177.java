import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SafeDirUtils {

    /**
     * Opens the file located at {@code filePath} and returns a reader for it
     * only if one of its parent directories is literally named "safedir".
     * If no such directory is found, the method returns {@code null}.
     *
     * @param filePath path to the file that should be opened
     * @return a BufferedReader for the file if the path is inside a "safedir"
     *         hierarchy; otherwise {@code null}
     * @throws IOException if the file cannot be opened
     */
    public static BufferedReader path2obj(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        Path dir = Paths.get(filePath).getParent();   // start with the file's directory
        while (dir != null) {
            if ("safedir".equals(dir.getFileName().toString())) {
                return reader;                        // found the sentinel directory
            }
            dir = dir.getParent();                   // climb up one level
        }

        // We never encountered "safedir" – close the stream and signal failure.
        reader.close();
        return null;
    }
}