import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileSafetyChecker {

    /**
     * Checks if the file at the given path is located within a directory named "safedir".
     * If so, returns an open FileInputStream to the file.
     * Otherwise, returns null.
     *
     * Note: This method opens the file immediately and returns the stream if the path
     * is valid. If the file is not in a "safedir" directory, the stream is not closed,
     * which may result in a resource leak. This behavior mirrors the original Python code.
     *
     * @param filePath The path to the file to be checked.
     * @return A FileInputStream if the file is in a "safedir" directory, null otherwise.
     * @throws IOException If the file cannot be opened.
     */
    public static FileInputStream path2obj(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream f = new FileInputStream(file);
        File directory = file.getParentFile();

        while (directory != null) {
            if ("safedir".equals(directory.getName())) {
                return f;
            }
            directory = directory.getParentFile();
        }

        return null;
    }
}