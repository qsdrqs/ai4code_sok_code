import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SafeOpener {

    /** 
     * If the (normalized, absolute) path starts with “safedir”, return an
     * InputStream to that file; otherwise return null.
     */
    public static InputStream openFile(String filename) throws IOException {
        // Equivalent to Path.resolve() + removing “..”
        Path path = Paths.get(filename).toAbsolutePath().normalize();

        /*
         * In the Python original, parts[0] is the root ("/") and parts[1]
         * must be "safedir".  In java.nio, getName(0) is the first path
         * component after the root, so we just check that.
         */
        if (path.getNameCount() >= 1 && "safedir".equals(path.getName(0).toString())) {
            // Will throw if the file does not exist – desired behaviour for the test
            return new FileInputStream(path.toFile());
        }
        return null;
    }

    /* --- Tests that mimic the original Python test() --- */
    public static void test() {
        try {
            assert openFile("/safedir/../a/b") == null;
        } catch (IOException e) {                          // should never happen here
            throw new AssertionError(e);
        }

        try {
            openFile("/safedir/a");                        // must throw (file missing)
            assert false;                                  // should be unreachable
        } catch (Exception ignored) {
            // Expected – any exception means the attempt to open the file
            // propagated, matching the Python semantics.
        }
    }

    public static void main(String[] args) {
        test();    // Run the small self-test
    }
}