import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SafeFileLoader {
    private static final String SAFE_DIR = "/safedir";
    
    public static BufferedReader safeLoadFile(String fname) {
        try {
            // Parse path - get canonical path (equivalent to realpath)
            Path path = Paths.get(fname);
            String fpath = path.toRealPath().toString();
            
            if (!fpath.startsWith(SAFE_DIR)) {
                System.out.println("FAILED");
                return null;
            }
            
            return new BufferedReader(new FileReader(fpath));
        } catch (IOException e) {
            System.out.println("FAILED");
            return null;
        }
    }
    
    public static void main(String[] args) {
        try {
            BufferedReader f = safeLoadFile("/safedir/test.txt");
            if (f != null) {
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = f.readLine()) != null) {
                    content.append(line).append("\n");
                }
                System.out.print(content.toString());
                f.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}