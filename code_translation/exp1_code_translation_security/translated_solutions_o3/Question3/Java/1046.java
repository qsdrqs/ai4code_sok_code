import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    /**
     * Mimics the Python implementation:
     *
     *     head, sep, tail = path.partition("/safedir")
     *     newpath = sep + tail
     *     return newpath.open()
     *
     * @param path original (possibly untrusted) path
     * @return an InputStream for the resulting “safe” path
     * @throws IOException if the file cannot be opened
     */
    public static InputStream openFile(Path path) throws IOException {

        final String SAFEDIR = "/safedir";

        // Convert the incoming Path to a String so we can “partition” it
        String pathStr = path.toString();

        int idx = pathStr.indexOf(SAFEDIR);

        /* Replicates Python’s logic:
           - If "/safedir" is present, keep everything from that point onward.
           - If not present, Python’s partition() would give sep == "" and
             tail == "", so newpath would become an empty string.
         */
        String newPathStr = (idx >= 0) ? pathStr.substring(idx) : "";

        // Create a java.nio.file.Path from the trimmed string
        Path newPath = Paths.get(newPathStr);

        // Open and return an InputStream (analogous to Path.open() in Python)
        return Files.newInputStream(newPath);
    }
}