import java.nio.file.Path;
import java.nio.file.Paths;

public class SafeDirExample {

    /**
     * Returns the Path object represented by {@code p} if, and only if,
     * the path is absolute (starts with “/”) and its first directory
     * component is exactly “safedir”.  Otherwise, returns {@code null}.
     */
    public static Path pathFromStr(String p) {
        Path path = Paths.get(p);

        // Must be absolute and start with “/”
        if (path.getRoot() == null || !"/".equals(path.getRoot().toString())) {
            return null;
        }

        // Must have at least one directory element after the root
        if (path.getNameCount() == 0) {
            return null;
        }

        // First directory must be "safedir"
        if (!"safedir".equals(path.getName(0).toString())) {
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