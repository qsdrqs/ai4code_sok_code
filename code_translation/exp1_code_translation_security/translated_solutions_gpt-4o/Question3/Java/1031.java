import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PathFinder {

    public static FileReader findPath(String path) {
        String safePath = "/safedir";
        File file = new File(path);
        String dirname = file.getParent();

        if (safePath.equals(dirname)) {
            try {
                FileReader fileReader = new FileReader(file);
                System.out.println("safe!");
                return fileReader;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("not safe!");
        }
        return null;
    }

    public static void main(String[] args) {
        findPath("/safedir/random.txt");
        findPath("/home/codex/Documents/User_Study_UI");
    }
}