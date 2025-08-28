import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample {

    public static Path pathFromStr(String p) {
        Path path = Paths.get(p);
        if (path.getNameCount() < 2 || !path.getName(0).toString().equals("safedir")) {
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