import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    private static final String SAFE_DIR = "/safedir";

    public static Object funcPath(String path) throws IOException {
        File requested = new File(path).getCanonicalFile();
        File safeRoot  = new File(SAFE_DIR).getCanonicalFile();

        // Ensure the resolved path is actually inside the safe directory
        if (requested.toPath().startsWith(safeRoot.toPath())) {
            return new FileInputStream(requested);
        } else {
            return "file not found or unsafe for access.";
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println(funcPath("/safedir/Jim"));
    }
}