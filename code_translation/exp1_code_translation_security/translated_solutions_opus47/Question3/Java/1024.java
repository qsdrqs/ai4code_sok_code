import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static Object returnObject(String path) {
        String rpath;
        try {
            File file = new File(path);
            rpath = file.getCanonicalPath();
        } catch (IOException e) {
            System.out.println("Error: " + e);
            return null;
        }
        
        if (rpath.startsWith("/safedir")) {
            return -1;
        }
        
        try {
            FileWriter fp = new FileWriter(path);
            return fp;
        } catch (IOException e) {
            System.out.println("Error: " + e);
            return null;
        }
    }
    
    public static void main(String[] args) {
        String fileName = "/tmp/foo";
        System.out.println(returnObject(fileName));
    }
}