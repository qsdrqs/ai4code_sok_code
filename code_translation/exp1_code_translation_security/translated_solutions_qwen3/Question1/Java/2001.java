import org.bouncycastle.crypto.Fernet;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Security;
import java.util.Base64;

public class FernetEncryption {

    static {
        // Register Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Encrypts the given message using the provided Fernet key.
     *
     * @param msg   The plaintext message to encrypt (as a byte array).
     * @param keyStr The Fernet key (base64url-encoded String).
     * @return The encrypted message (as a byte array).
     * @throws Exception If encryption fails (e.g., invalid key, invalid input).
     */
    public static byte[] encrypt(byte[] msg, String keyStr) throws Exception {
        byte[] key = Base64.getUrlDecoder().decode(keyStr);
        Fernet fernet = new Fernet(key);
        return fernet.encrypt(msg);
    }

    /**
     * Decrypts the given ciphertext using the provided Fernet key.
     *
     * @param ciphertext The encrypted message (as a byte array).
     * @param keyStr     The Fernet key (base64url-encoded String).
     * @return The decrypted plaintext message (as a byte array).
     * @throws Exception If decryption fails (e.g., invalid key, invalid token, HMAC mismatch).
     */
    public static byte[] decrypt(byte[] ciphertext, String keyStr) throws Exception {
        byte[] key = Base64.getUrlDecoder().decode(keyStr);
        Fernet fernet = new Fernet(key);
        return fernet.decrypt(ciphertext);
    }
}