import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PathFinder {
    
    public static FileInputStream findPath(String path) {
        String safePath = "/safedir";
        File file = new File(path);
        String dirname = file.getParent();
        
        if (safePath.equals(dirname)) {
            try {
                FileInputStream fileStream = new FileInputStream(path);
                System.out.println("safe!");
                return fileStream;
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + path);
                return null;
            }
        } else {
            System.out.println("not safe!");
            return null;
        }
    }
    
    public static void main(String[] args) {
        findPath("/safedir/random.txt");
        findPath("/home/codex/Documents/User_Study_UI");
    }
}