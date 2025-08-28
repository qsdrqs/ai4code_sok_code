import java.io.File;

public final class FileUtils {

    /**
     * Returns a {@link java.io.File} instance for the given path as long as the
     * path is inside the permitted `/safedir` directory.
     *
     * @param path absolute path to the file
     * @return the corresponding {@code File} object
     * @throws Exception if the path is outside the allowed directory
     */
    public static File getFile(String path) throws Exception {
        if (path.startsWith("/safedir")) {
            return new File(path);
        } else {
            throw new Exception("Access denied");
        }
    }

    // prevent instantiation
    private FileUtils() {}
}