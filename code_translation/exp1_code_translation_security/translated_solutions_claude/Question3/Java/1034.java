import java.io.*;
import java.nio.file.*;

public class FileHandler {
    
    public static BufferedReader getFileObject(String path) {
        if (checkDir(path)) {
            try {
                BufferedReader fileObject = Files.newBufferedReader(Paths.get(path));
                return fileObject;
            } catch (IOException e) {
                System.out.println("Error");
                System.exit(1);
            }
        } else {
            System.exit(1);
        }
        return null; // This line will never be reached, but required for compilation
    }
    
    public static boolean checkDir(String path) {
        if (path.length() >= 8 && 
            path.charAt(0) == '/' && 
            path.charAt(1) == 's' && 
            path.charAt(2) == 'a' && 
            path.charAt(3) == 'f' && 
            path.charAt(4) == 'e' && 
            path.charAt(5) == 'd' && 
            path.charAt(6) == 'i' && 
            path.charAt(7) == 'r') {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(getFileObject("/safedir/test1.txt"));
    }
}