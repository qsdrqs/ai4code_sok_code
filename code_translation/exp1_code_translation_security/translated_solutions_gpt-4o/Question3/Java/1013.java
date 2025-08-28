import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOpener {

    public static FileReader openFile(String path) {
        if (inSafe(path)) {
            try {
                return new FileReader(path);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    private static boolean inSafe(String fname) {
        Path cwd = Paths.get("/safedir").normalize();
        Path filePath = Paths.get(new File(fname).getAbsolutePath()).getParent().normalize();
        return filePath.startsWith(cwd);
    }

    public static void main(String[] args) {
        FileReader fileReader = openFile("/env.sh");
        if (fileReader != null) {
            System.out.println("File opened successfully.");
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("File could not be opened.");
        }
    }
}