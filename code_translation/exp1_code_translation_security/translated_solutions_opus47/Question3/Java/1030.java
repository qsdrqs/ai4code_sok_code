import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    
    static class PathNotAllowed extends Exception {
        public PathNotAllowed(String message) {
            super(message);
        }
    }
    
    public static FileReader getFilePath(String path) throws PathNotAllowed, FileNotFoundException {
        if (!path.startsWith("/safedir")) {
            throw new PathNotAllowed("filepath not allowed, only /safedir/... file paths are accessible");
        }
        return new FileReader(path);
    }
    
    public static void main(String[] args) throws PathNotAllowed, IOException {
        String prefix = "/safedir/";
        
        String[] goodPaths = {
            prefix + "test1.txt",
            prefix + "test.txt"
        };
        
        // should open without exception
        for (String path : goodPaths) {
            FileReader f = getFilePath(path);
            f.close();
        }
        
        // files not existing should fail as expected
        FileReader f1 = null;
        try {
            f1 = getFilePath("/safedir/noexist.txt");
            f1.close();
        } catch (FileNotFoundException e) {
            // expected - pass
        } finally {
            if (f1 != null) {
                f1.close();
            }
        }
        
        // files outside of not allowed path should raise PathNotAllowed exception
        FileReader f2 = null;
        try {
            f2 = getFilePath("not/a/good/path.txt");
            f2.close();
        } catch (PathNotAllowed e) {
            // expected - pass
        } finally {
            if (f2 != null) {
                f2.close();
            }
        }
    }
}