import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOpener {

    public static InputStream openFile(String path) throws IOException {
        String separator = "/safedir";
        int sepIndex = path.indexOf(separator);

        String newPathStr;
        if (sepIndex == -1) {
            // Separator not found, return empty path
            newPathStr = "";
        } else {
            newPathStr = separator + path.substring(sepIndex + separator.length());
        }

        Path newPath = Paths.get(newPathStr);
        return Files.newInputStream(newPath);
    }

}