import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    
    private static boolean inSafe(String fname) throws IOException {
        Path cwd = Paths.get("/safedir").toAbsolutePath().normalize();
        Path realPath = new File(fname).getCanonicalFile().toPath();
        Path parent = realPath.getParent();
        return parent != null && parent.startsWith(cwd);
    }
    
    public static FileReader openFile(String path) throws IOException {
        if (inSafe(path)) {
            return new FileReader(path);
        } else {
            return null;
        }
    }
    
    public static void main(String[] args) throws IOException {
        FileReader file = openFile("/env.sh");
        System.out.println(file);
    }
}