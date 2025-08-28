import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Utility class that exposes a “safe” way to obtain a handle
 * to a file that must live inside “/safedir”.
 *
 * All dependencies (java.nio.file.* and java.io.*) are part of
 * the standard JDK.
 */
public final class SafeOpen {

    /** Absolute, already–resolved reference to /safedir */
    private static final Path SAFE_DIR;

    static {
        Path tmp;
        try {
            // Resolve /safedir itself (follows any symlinks inside it)
            tmp = Paths.get("/safedir").toRealPath();
        } catch (IOException e) {
            // If /safedir does not exist yet, fall back to a
            // normalised absolute path so the prefix test still works.
            tmp = Paths.get("/safedir").toAbsolutePath().normalize();
        }
        SAFE_DIR = tmp;
    }

    private SafeOpen() { /* no instances */ }

    /**
     * Return a {@link java.nio.file.Path} that refers to a *regular file*
     * located inside “/safedir”.
     *
     * If the file
     *   • does not exist,  
     *   • lies outside “/safedir”, or  
     *   • is not a plain file (directory, FIFO, etc.),  
     * or if it is a symlink that ultimately resolves outside “/safedir”,
     * this method returns {@code null}.
     *
     * Callers can use the returned Path with
     * {@code Files.newInputStream(path)}, {@code Files.readString(path)},
     * and similar APIs.
     *
     * @param userPath Any path supplied by the caller.
     * @return A secure, resolved Path inside /safedir, or {@code null}.
     */
    public static Path safeOpen(String userPath) {
        try {
            /* 1. Resolve the path, following any symlinks *inside* it.
                   NOFOLLOW_LINKS prevents toRealPath from following the
                   *final* component if that final component itself is a
                   symbolic link; we want to inspect that ourselves.          */
            Path realPath = Paths.get(userPath)
                                 .toRealPath(LinkOption.NOFOLLOW_LINKS);

            /* 2. Ensure the resolved location is within /safedir. */
            if (!realPath.startsWith(SAFE_DIR)) {
                return null;
            }

            /* 3. Must be a regular, non-directory file. */
            if (!Files.isRegularFile(realPath, LinkOption.NOFOLLOW_LINKS)) {
                return null;
            }

            return realPath;          // Success!
        } catch (IOException | SecurityException ex) {
            // Anything that makes the lookup unsafe or impossible:
            // file not found, permission issue, etc.
            return null;
        }
    }
}