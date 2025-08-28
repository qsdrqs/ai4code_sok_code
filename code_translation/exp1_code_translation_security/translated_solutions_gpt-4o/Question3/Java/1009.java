import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.io.BufferedReader;

public class FileHandler {
    private static final String SAFE = "/safedir/";

    public static BufferedReader getFile(String path) throws IOException {
        Path safePath = Paths.get(SAFE).toRealPath();
        Path testPath = safePath.resolve(path).toRealPath();

        if (!testPath.startsWith(safePath)) {
            throw new IOException("Filename " + testPath + " is not in " + safePath + " directory");
        }

        return Files.newBufferedReader(testPath, StandardOpenOption.READ);
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = getFile("example.txt");
            // Use the reader to read the file
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}