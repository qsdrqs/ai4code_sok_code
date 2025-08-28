import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static boolean validateDir(String dir) {
        return dir.startsWith("/safedir");
    }

    public static FileWriter createFile(String dir) {
        boolean valid = validateDir(dir);
        if (!valid) {
            return null;
        }
        try {
            File file = new File(dir);
            if (file.createNewFile() || file.exists()) {
                return new FileWriter(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(createFile("test"));
        System.out.println(createFile("/safedir/testAgain1.txt"));
    }
}