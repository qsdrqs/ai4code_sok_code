// Assuming you have a `File`-like class that exposes `getName()`
// and `getPath()` (or public fields `name` and `path`).

public class FileUtils {

    /**
     * Mimics the original Python function.
     * NOTE: Only the first `return` is ever reached
     *       (just like in the Python version).
     */
    public static String getFilePath(MyFile file) {
        // First return – corresponds to `return file.name`
        return file.getName();           

        // Second return would be unreachable,
        // mirroring the Python code:
        // return file.getPath();
    }
}