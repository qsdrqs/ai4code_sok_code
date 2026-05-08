import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SafeFileLoader {
    private static final String SAFE_DIR = "/safedir";
    
    public static BufferedReader safeLoadFile(String fname) throws IOException {
        // Parse path
        String fpath = new File(fname).getCanonicalPath();
        if (!fpath.startsWith(SAFE_DIR)) {
            System.out.println("FAILED");
            return null;
        }
        return new BufferedReader(new FileReader(fpath));
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader f = safeLoadFile("/safedir/test.txt");
        StringBuilder content = new StringBuilder();
        int c;
        while ((c = f.read()) != -1) {
            content.append((char) c);
        }
        System.out.println(content.toString());
        f.close();
    }
}