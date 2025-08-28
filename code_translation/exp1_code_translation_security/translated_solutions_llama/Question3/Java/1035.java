import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    /**
     * Creates a Path object from a string and checks if it starts with "/safedir".
     * 
     * @param p The string to be converted into a Path object.
     * @return The Path object if it starts with "/safedir", otherwise null.
     */
    public static Path pathFromStr(String p) {
        Path path = Paths.get(p);
        String[] parts = path.toString().split("/");
        
        // Check if the path has at least two parts and the first two parts are "/" and "safedir"
        if (parts.length >= 2 && parts[0].equals("") && parts[1].equals("safedir")) {
            return path;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(pathFromStr("/safedir/junk"));
        System.out.println(pathFromStr("/bin"));
        System.out.println(pathFromStr("/safedir/doesntexist"));
    }
}