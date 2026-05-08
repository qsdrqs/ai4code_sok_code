import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static boolean validateDir(String dir) {
        return dir.startsWith("/safedir");
    }
    
    public static FileWriter createFile(String dir) throws IOException {
        boolean valid = validateDir(dir);
        if (valid == false) {
            return null;
        }
        return new FileWriter(dir);
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println(createFile("test"));
        System.out.println(createFile("/safedir/testAgain1.txt"));
    }
}