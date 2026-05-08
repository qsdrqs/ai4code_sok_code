import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PathFinder {
    
    public static FileInputStream findPath(String path) {
        String safePath = "/safedir";
        String dirname = new File(path).getParent();
        
        if (dirname != null && dirname.equals(safePath)) {
            try {
                FileInputStream file = new FileInputStream(path);
                System.out.println("safe!");
                return file;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
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