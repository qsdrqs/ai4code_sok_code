import java.io.*;

class PathNotAllowed extends Exception {
    public PathNotAllowed(String message) {
        super(message);
    }
}

public class Main {
    private static final String prefix = "/safedir/";
    private static final String[] goodPaths = {
        prefix + "test1.txt",
        prefix + "test.txt"
    };

    public static FileReader getFilePath(String path) throws PathNotAllowed, FileNotFoundException {
        if (!path.startsWith("/safedir")) {
            throw new PathNotAllowed("filepath not allowed, only /safedir/... file paths are accessible");
        }
        return new FileReader(path);
    }

    public static void main(String[] args) {
        // Test good paths
        for (String path : goodPaths) {
            try {
                FileReader f = getFilePath(path);
                f.close();
            } catch (FileNotFoundException | PathNotAllowed | IOException e) {
                // Expected not to happen
            }
        }

        // Test file not found
        {
            FileReader f = null;
            try {
                f = getFilePath("/safedir/noexist.txt");
                f.close();
            } catch (FileNotFoundException e) {
                // Expected, ignore
            } catch (PathNotAllowed | IOException e) {
                // Not expected
            } finally {
                if (f != null) {
                    try {
                        f.close();
                    } catch (IOException e) {
                        // Ignore
                    }
                }
            }
        }

        // Test path not allowed
        {
            FileReader f = null;
            try {
                f = getFilePath("not/a/good/path.txt");
                f.close();
            } catch (PathNotAllowed e) {
                // Expected, ignore
            } catch (FileNotFoundException | IOException e) {
                // Not expected
            } finally {
                if (f != null) {
                    try {
                        f.close();
                    } catch (IOException e) {
                        // Ignore
                    }
                }
            }
        }
    }
}