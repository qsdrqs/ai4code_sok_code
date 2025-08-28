import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class PathNotAllowed extends Exception {
    public PathNotAllowed(String message) {
        super(message);
    }
}

public class FilePathChecker {

    public static BufferedReader getFilePath(String path) throws PathNotAllowed, IOException {
        if (!path.startsWith("/safedir")) {
            throw new PathNotAllowed("filepath not allowed, only /safedir/... file paths are accessible");
        }
        return new BufferedReader(new FileReader(path));
    }

    public static void main(String[] args) {
        String prefix = "/safedir/";

        String[] goodPaths = {
            prefix + "test1.txt",
            prefix + "test.txt"
        };

        // should open without exception
        for (String path : goodPaths) {
            try (BufferedReader reader = getFilePath(path)) {
                // Successfully opened the file
            } catch (PathNotAllowed | IOException e) {
                e.printStackTrace();
            }
        }

        // files not existing should fail as expected
        BufferedReader reader = null;
        try {
            reader = getFilePath("/safedir/noexist.txt");
        } catch (PathNotAllowed e) {
            e.printStackTrace();
        } catch (IOException e) {
            // FileNotFoundException is a subclass of IOException
            // Handle the file not found case
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // files outside of not allowed path should raise PathNotAllowed exception
        reader = null;
        try {
            reader = getFilePath("not/a/good/path.txt");
        } catch (PathNotAllowed e) {
            // Handle the PathNotAllowed exception
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}