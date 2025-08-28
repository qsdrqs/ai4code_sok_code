import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class provides a method to validate whether a given path string starts with
 * the root directory '/' and the first subdirectory 'safedir'.
 */
public class PathChecker {

    /**
     * Validates if the given path string starts with the root directory '/' and
     * the first subdirectory 'safedir'.
     *
     * @param p The path string to validate.
     * @return The Path object if the path starts with '/safedir', otherwise null.
     */
    public static Path path_from_str(String p) {
        // Convert the input string to a Path object
        Path path = Paths.get(p);

        // Check if the path is absolute and has at least one name component
        if (path.isAbsolute() && path.getNameCount() > 0) {
            // Check if the first name component is "safedir"
            if ("safedir".equals(path.getName(0).toString())) {
                return path;
            }
        }

        // Return null if the path does not meet the criteria
        return null;
    }

    /**
     * Main method to test the path_from_str function with sample inputs.
     */
    public static void main(String[] args) {
        // Test cases
        System.out.println(path_from_str("/safedir/junk"));       // Expected: /safedir/junk
        System.out.println(path_from_str("/bin"));                // Expected: null
        System.out.println(path_from_str("/safedir/doesntexist")); // Expected: /safedir/doesntexist
    }
}