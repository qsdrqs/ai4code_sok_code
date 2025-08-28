import java.nio.file.Path;
import java.nio.file.Paths;

public class PathValidator {
    
    public static Path pathFromStr(String p) {
        Path path = Paths.get(p);
        
        // Get the path components
        String[] parts = path.toString().split("/");
        
        // Check if path starts with "/safedir"
        // For absolute paths starting with "/", the first element after split will be empty
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