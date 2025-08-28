import java.nio.charset.StandardCharsets;

/*  A thin Java wrapper that behaves the same way as the original Python
 *  functions.  A compatible Fernet implementation for Java is assumed to
 *  be present on the class-path and to expose the same two methods used
 *  below (`encrypt` and `decrypt`).
 *
 *  If you rely on a concrete library such as
 *  ‘com.macasaet.fernet:fernet-java8’ or ‘dev.tauri:fernet’, just replace
 *  the fully-qualified class name in the import statement accordingly.
 */
import fernet.Fernet;   // <- the concrete Fernet class will be provided

public final class CryptoUtils {

    private CryptoUtils() { /* utility class – no instances */ }

    /**
     * Encrypt a UTF-8 string with the supplied Fernet key.
     *
     * @param message the plain-text message
     * @param key     the Base64-encoded Fernet key
     * @return        the encrypted token (Base64-encoded bytes)
     */
    public static byte[] encrypt(final String message, final String key) {
        Fernet fernet = new Fernet(key);
        return fernet.encrypt(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decrypt a Fernet token back to its original bytes.
     *
     * @param token the encrypted token (as returned by {@link #encrypt})
     * @param key   the same Base64-encoded Fernet key that was used
     * @return      the decrypted bytes (caller may convert to String)
     */
    public static byte[] decrypt(final byte[] token, final String key) {
        Fernet fernet = new Fernet(key);
        return fernet.decrypt(token);
    }
}