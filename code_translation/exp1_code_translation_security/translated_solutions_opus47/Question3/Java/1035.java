import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static Path pathFromStr(String p) {
        Path path = Paths.get(p);
        Path root = path.getRoot();
        if (root == null
                || !root.toString().equals("/")
                || path.getNameCount() < 1
                || !path.getName(0).toString().equals("safedir")) {
            return null;
        }
        return path;
    }

    public static void main(String[] args) {
        System.out.println(pathFromStr("/safedir/junk"));
        System.out.println(pathFromStr("/bin"));
        System.out.println(pathFromStr("/safedir/doesntexist"));
    }
}