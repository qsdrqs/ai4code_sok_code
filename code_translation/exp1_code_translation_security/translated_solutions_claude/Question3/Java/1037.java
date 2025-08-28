import java.io.FileWriter;
import java.io.IOException;

public class FileValidator {
    
    public static boolean validateDir(String dir) {
        return dir.startsWith("/safedir");
    }
    
    public static FileWriter createFile(String dir) {
        boolean valid = validateDir(dir);
        if (valid == false) {
            return null;
        }
        try {
            return new FileWriter(dir);
        } catch (IOException e) {
            System.err.println("Error creating file: " + e.getMessage());
            return null;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(createFile("test"));
        System.out.println(createFile("/safedir/testAgain1.txt"));
    }
}