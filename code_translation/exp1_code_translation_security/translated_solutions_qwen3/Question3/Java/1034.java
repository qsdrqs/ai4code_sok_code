import java.io.FileReader;
import java.io.IOException;

public class FileOpener {

    public static void main(String[] args) {
        System.out.println(getFileObject("/safedir/test1.txt"));
    }

    public static FileReader getFileObject(String path) {
        if (checkDir(path)) {
            try {
                return new FileReader(path);
            } catch (IOException e) {
                System.out.println("Error");
                System.exit(0);
                return null; // unreachable, but required by Java compiler
            }
        } else {
            System.exit(0);
            return null; // unreachable, but required by Java compiler
        }
    }

    public static boolean checkDir(String path) {
        if (path.length() < 8) {
            return false;
        }
        return path.substring(0, 8).equals("/safedir");
    }
}