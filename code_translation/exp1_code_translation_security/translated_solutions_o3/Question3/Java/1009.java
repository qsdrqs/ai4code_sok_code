import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

/**
 * Rough, line-for-line translation of the given Python code.
 */
public final class SafeFile {

    // constant equivalent to Python’s `SAFE = "/safedir/"`
    private static final Path SAFE = Paths.get("/safedir/").toAbsolutePath().normalize();

    /**
     * Equivalent to Python’s  getFile(path)  function.
     *
     * @param path the relative path (inside the safe directory) the caller wants to open
     * @return     a BufferedReader for reading the file
     * @throws IOException        if the file can’t be opened/read
     * @throws SecurityException  if the resolved file is not inside SAFE
     */
    public static BufferedReader getFile(String path) throws IOException {

        // Build a candidate path inside SAFE and resolve it to its canonical form
        Path testPath = SAFE.resolve(path)          // append requested path to the safe dir
                             .toRealPath(LinkOption.NOFOLLOW_LINKS); // resolve symlinks/“..”

        // Make sure the resulting path is still inside SAFE
        if (!testPath.startsWith(SAFE)) {
            throw new SecurityException(
                    "Filename " + testPath + " is not in " + SAFE + " directory");
        }

        // Open the file for reading (text mode, UTF-8) and return it
        return Files.newBufferedReader(testPath, StandardCharsets.UTF_8);
    }

    // Prevent instantiation
    private SafeFile() { }
}