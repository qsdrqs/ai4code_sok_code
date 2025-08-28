import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// Custom exception for paths not allowed
class PathNotAllowed extends Exception {
    public PathNotAllowed(String message) {
        super(message);
    }
}

public class Main {

    /**
     * Opens a file for reading if its path starts with the allowed prefix.
     *
     * @param path The path to the file.
     * @return A FileReader object for the file.
     * @throws PathNotAllowed If the path does not start with the allowed prefix.
     * @throws FileNotFoundException If the file does not exist.
     */
    public static FileReader getFilePath(String path) throws PathNotAllowed, FileNotFoundException {
        String allowedPrefix = "/safedir";
        if (!path.startsWith(allowedPrefix)) {
            throw new PathNotAllowed("File path not allowed, only /safedir/... file paths are accessible");
        }
        return new FileReader(path);
    }

    public static void main(String[] args) {
        String prefix = "/safedir/";
        String[] goodPaths = {
            prefix + "test1.txt",
            prefix + "test.txt"
        };

        // Should open without exception
        for (String path : goodPaths) {
            try (FileReader f = getFilePath(path)) {
                // File is open, do something
            } catch (PathNotAllowed | FileNotFoundException e) {
                System.out.println("Unexpected exception: " + e.getMessage());
            }
        }

        // Files not existing should fail as expected
        try (FileReader f = getFilePath("/safedir/noexist.txt")) {
            // This should not be reached
        } catch (PathNotAllowed e) {
            System.out.println("Unexpected PathNotAllowed exception: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Expected FileNotFoundException: " + e.getMessage());
        }

        // Files outside of not allowed path should raise PathNotAllowed exception
        try (FileReader f = getFilePath("not/a/good/path.txt")) {
            // This should not be reached
        } catch (PathNotAllowed e) {
            System.out.println("Expected PathNotAllowed exception: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Unexpected FileNotFoundException: " + e.getMessage());
        }
    }
}