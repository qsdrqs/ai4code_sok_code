import java.io.File;
import java.io.FileReader;

public class FileOpener {

    public static FileReader openFile(String path) throws Exception {
        File input = new File(path);
        File safeDir = new File("/safedir");

        String inputAbs = input.getAbsolutePath();
        String safeDirAbs = safeDir.getAbsolutePath();

        if (inputAbs.startsWith(safeDirAbs)) {
            return new FileReader(path);
        } else {
            throw new Exception("Access Denied");
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(openFile("/safedir/test.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}