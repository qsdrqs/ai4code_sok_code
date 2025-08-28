import java.io.*;

public class SafeLoader {
    private static final String SAFE_DIR = "/safedir";

    public static FileReader safeLoadFile(String fname) throws IOException {
        File file = new File(fname);
        String fpath = file.getCanonicalPath();
        if (!fpath.startsWith(SAFE_DIR)) {
            System.out.println("FAILED");
            return null;
        }
        return new FileReader(file);
    }

    public static void main(String[] args) {
        try {
            FileReader f = safeLoadFile("/safedir/test.txt");
            int c;
            while ((c = f.read()) != -1) {
                System.out.print((char) c);
            }
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}