import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Direct Java translation of the given Python snippet.
 */
public class SafeFileExample {

    /* Custom exception mirroring the Python PathNotAllowed exception */
    public static class PathNotAllowed extends Exception {
        public PathNotAllowed(String message) {
            super(message);
        }
    }

    /**
     * Equivalent to the Python getFilePath() helper.
     *
     * @param path File-system path requested by the caller
     * @return     java.io.Reader pointing to that file
     * @throws PathNotAllowed     If the file is outside of "/safedir"
     * @throws FileNotFoundException If the file can't be found on disk
     */
    public static Reader getFilePath(String path)
            throws PathNotAllowed, FileNotFoundException {
        if (!path.startsWith("/safedir")) {
            throw new PathNotAllowed(
                "filepath not allowed, only /safedir/... file paths are accessible");
        }
        // Java's FileReader opens the file in read-only mode
        return new FileReader(path);
    }

    /* -------------------------------------------------- */

    public static void main(String[] args) {

        final String prefix = "/safedir/";

        // Paths that should succeed (assuming they exist on disk)
        String[] goodPaths = {
            prefix + "test1.txt",
            prefix + "test.txt"
        };

        /* should open without exception */
        for (String path : goodPaths) {
            Reader f = null;
            try {
                f = getFilePath(path);
                // ... do something with the file if desired ...
            } catch (IOException | PathNotAllowed e) {
                // Unexpected for the good paths (aside from a missing file, which
                //       would be an IOException / FileNotFoundException)
                e.printStackTrace();
            } finally {
                closeQuietly(f);
            }
        }

        /* files not existing should fail as expected */
        Reader f = null;
        try {
            f = getFilePath("/safedir/noexist.txt");
        } catch (FileNotFoundException e) {
            // expected: file is missing
        } catch (PathNotAllowed e) {
            // should not happen here
        } finally {
            closeQuietly(f);
        }

        /* files outside the allowed path should raise PathNotAllowed */
        f = null;
        try {
            f = getFilePath("not/a/good/path.txt");
        } catch (PathNotAllowed e) {
            // expected: path is disallowed
        } catch (FileNotFoundException e) {
            // should not happen, because the path should be blocked first
        } finally {
            closeQuietly(f);
        }
    }

    /* Utility: silent close helper (mirrors the Python f.close() in finally) */
    private static void closeQuietly(Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException ignored) {}
        }
    }
}