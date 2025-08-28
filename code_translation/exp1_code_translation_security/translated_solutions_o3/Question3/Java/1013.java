import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Rough Java translation of the given Python example.
 * All required classes (java.io, java.nio.file, etc.) are part of the
 * standard JDK so no extra libraries are assumed.
 */
public class Main {

    /**
     * Returns {@code true} if {@code fname} resides (after canonical
     * resolution) somewhere inside “/safedir”.  Mimics the intent of the
     * Python helper in_safe().
     */
    private static boolean inSafe(String fname) throws IOException {
        Path cwd  = Paths.get("/safedir").toAbsolutePath().normalize();
        Path real = Paths
                .get(fname)
                .toRealPath()          // resolve symlinks, “..”, etc.
                .getParent()           // directory that contains the file
                .normalize();

        /* 
         * Paths.startWith(other) is the canonical way to ask
         * “is real an ancestor or the same as cwd” in Java.
         */
        return real.startsWith(cwd);
    }

    /**
     * Mimics the Python openFile() routine:
     *   – If the path is inside /safedir, open the file for reading and
     *     return a Reader (here FileReader).
     *   – Otherwise return {@code null}.
     *
     * Any I/O‐related problems also result in {@code null}, mirroring the
     * silent-failure style of the original snippet.
     */
    public static Reader openFile(String path) {
        try {
            if (inSafe(path)) {
                return new FileReader(Paths.get(path).toFile());
            }
        } catch (IOException ignored) {
            /* Fall through and return null just like the Python version. */
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(openFile("/env.sh"));
    }
}