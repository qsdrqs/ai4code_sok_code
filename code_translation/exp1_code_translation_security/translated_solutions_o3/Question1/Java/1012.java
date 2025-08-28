import java.nio.charset.StandardCharsets;

/*  The required Fernet implementation (together with its transitive
    dependencies) will be on the class-path when this code is compiled /
    executed, so we can reference it directly here.               */
import com.example.crypto.Fernet;          // <-- supplied dependency

public final class CryptoUtils {

    /* Equivalent to the Python statement
           key = Fernet.generate_key()
       We create one application-wide key that will be reused every time
       encryptText(..) / decryptText(..) is called.                        */
    private static final byte[] KEY = Fernet.generateKey();

    private CryptoUtils() {
        /* utility class: prevent instantiation */
    }

    /**
     * Encrypt a UTF-8 text using the previously generated Fernet key.
     *
     * @param plainText clear text to be encrypted
     * @return base-64 encoded cipher text
     */
    public static String encryptText(final String plainText) {
        Fernet f = new Fernet(KEY);
        byte[] cipherBytes = f.encrypt(plainText.getBytes(StandardCharsets.UTF_8));

        /*  In the original Python code the encrypted value (bytes) is
            turned into a String by calling .decode().  We do the same
            here by building a new String with UTF-8 semantics.            */
        return new String(cipherBytes, StandardCharsets.UTF_8);
    }

    /**
     * Decrypt a previously generated cipher text.
     *
     * @param encryptedText base-64 encoded cipher text returned by
     *                      {@link #encryptText(String)}
     * @return the original clear text
     */
    public static String decryptText(final String encryptedText) {
        Fernet f = new Fernet(KEY);
        byte[] plainBytes = f.decrypt(encryptedText.getBytes(StandardCharsets.UTF_8));

        return new String(plainBytes, StandardCharsets.UTF_8);
    }
}