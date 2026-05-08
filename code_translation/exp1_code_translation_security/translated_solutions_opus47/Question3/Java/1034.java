import java.io.FileReader;
import java.io.IOException;

public class Main {
    
    public static FileReader getFileObject(String path) {
        FileReader fileObject = null;
        if (checkDir(path)) {
            try {
                fileObject = new FileReader(path);
            } catch (IOException e) {
                System.out.println("Error");
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
        return fileObject;
    }
    
    public static boolean checkDir(String path) {
        if (path.charAt(0) == '/' && path.charAt(1) == 's' && path.charAt(2) == 'a' 
            && path.charAt(3) == 'f' && path.charAt(4) == 'e' && path.charAt(5) == 'd' 
            && path.charAt(6) == 'i' && path.charAt(7) == 'r') {
            return true;
        } else {
            return false;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(getFileObject("/safedir/test1.txt"));
    }
}