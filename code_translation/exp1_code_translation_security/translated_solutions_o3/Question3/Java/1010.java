import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Utility methods that deal with file-handling.
 */
public final class FileUtils {

    private FileUtils() {
        /* static utility class – no instances allowed */
    }

    /**
     * Tries to open {@code filename} for writing, but only if the path is
     * strictly inside <code>/safedir/</code> and does not contain any
     * parent-directory references such as "<code>/../</code>".
     *
     * @param filename absolute path to the file that should be opened
     * @return an {@link OutputStream} ready for writing, or {@code null} when
     *         the security checks fail or the file cannot be opened
     */
    public static OutputStream openPath(String filename) {

        /* ---- Security checks ------------------------------------------- */

        // 1) Must start with "/safedir/" – prevents path traversal above that
        if (!filename.startsWith("/safedir/")) {
            return null;
        }

        // 2) Reject any embedded "/../" segments – conservative but safe
        if (filename.contains("/../")) {
            return null;
        }

        /* ---- Attempt to open the file ---------------------------------- */
        try {
            // equivalent to Python's open(filename, 'w')
            return new FileOutputStream(filename);
        } catch (FileNotFoundException ex) {
            // Could not create/open the file; signal failure via null
            return null;
        }
    }

    /* Example usage */
    public static void main(String[] args) {
        OutputStream stream = openPath("/safedir/Jim");
        if (stream == null) {
            System.out.println("Invalid path or unable to open the file.");
        } else {
            System.out.println("File successfully opened.");
            try {
                stream.close(); // remember to close when done
            } catch (Exception ignored) {
            }
        }
    }
}