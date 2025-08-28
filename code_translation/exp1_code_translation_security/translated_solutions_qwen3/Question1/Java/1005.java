import java.security.SecureRandom;
import java.util.Base64;

// Assuming a compatible Fernet class is available in the classpath
// For example, from a library like jfernet or auth0/fernet-java
import com.auth0.jwt.cryptography.Fernet;

public class FernetExample {

    public static void main(String[] args) {
        // Generate a 32-byte key, encoded as URL-safe Base64 (same as Python's Fernet.generate_key())
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32];
        random.nextBytes(keyBytes);
        String key = Base64.getUrlEncoder().encodeToString(keyBytes);

        // Original message
        String msg = "a much longer message with punctuation!";

        // Encrypt and decrypt
        String encrypted = encrypt(msg, key);
        String decrypted = decrypt(encrypted, key);

        // Output results
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }

    /**
     * Encrypts a message using Fernet symmetric encryption.
     *
     * @param msg the message to encrypt
     * @param key the Fernet key (URL-safe Base64 encoded)
     * @return the encrypted token as a string
     */
    public static String encrypt(String msg, String key) {
        Fernet fernet = new Fernet(key);
        return fernet.encrypt(msg);
    }

    /**
     * Decrypts a message using Fernet symmetric encryption.
     *
     * @param token the encrypted token
     * @param key   the Fernet key (URL-safe Base64 encoded)
     * @return the decrypted message
     */
    public static String decrypt(String token, String key) {
        Fernet fernet = new Fernet(key);
        return fernet.decrypt(token);
    }
}