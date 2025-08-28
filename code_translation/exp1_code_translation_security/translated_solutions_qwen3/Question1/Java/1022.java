import com.macasaet.fernet.Fernet;
import com.macasaet.fernet.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class FernetExample {

    public static void main(String[] args) {
        // Generate a 32-byte key encoded in URL-safe Base64
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32];
        random.nextBytes(keyBytes);
        String keyString = Base64.getUrlEncoder().encodeToString(keyBytes);
        Key exKey = new Key(keyString);

        // Encrypt a message
        String encrypted = encrypt("Hello World!", exKey);
        System.out.println("Encrypted: " + encrypted);

        // Decrypt the message
        String decrypted = decrypt(encrypted, exKey);
        System.out.println("Decrypted: " + decrypted);
    }

    /**
     * Encrypts a string using Fernet with the provided key.
     *
     * @param input the plaintext string to encrypt
     * @param key   the Fernet key
     * @return the encrypted token as a Base64-encoded string
     */
    public static String encrypt(String input, Key key) {
        Fernet fernet = new Fernet(key);
        return fernet.encrypt(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decrypts a Fernet-encrypted token using the provided key.
     *
     * @param encrypted the encrypted token
     * @param key       the Fernet key
     * @return the decrypted plaintext string
     */
    public static String decrypt(String encrypted, Key key) {
        Fernet fernet = new Fernet(key);
        byte[] decryptedBytes = fernet.decrypt(encrypted);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}