import java.io.FileReader;
import java.io.IOException;

public class SafeDirReader {

    public static FileReader getFileObject(String path) {
        if (checkDir(path)) {
            try {
                return new FileReader(path);
            } catch (IOException e) {
                System.out.println("Error");
                System.exit(1);                 // terminate like the Python code
            }
        } else {
            System.exit(1);                     // terminate if directory check fails
        }
        return null;    // will never be reached, but required by the compiler
    }

    public static boolean checkDir(String path) {
        // Exact character-by-character match for “/safedir”
        // (mimics the original Python logic)
        return path.length() >= 8 &&
               path.charAt(0) == '/' &&
               path.charAt(1) == 's' &&
               path.charAt(2) == 'a' &&
               path.charAt(3) == 'f' &&
               path.charAt(4) == 'e' &&
               path.charAt(5) == 'd' &&
               path.charAt(6) == 'i' &&
               path.charAt(7) == 'r';
    }

    public static void main(String[] args) {
        System.out.println(getFileObject("/safedir/test1.txt"));
    }
}