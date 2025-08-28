import com.github.voodoodyne.security.fernet.Fernet;
import java.nio.charset.StandardCharsets;

public class FernetHandler {

    /**
     * Encrypts a message using Fernet encryption.
     *
     * @param message The plaintext message to encrypt.
     * @param key     The 32-byte Fernet key.
     * @return The encrypted message as a byte array (UTF-8 encoded base64 string).
     */
    public static byte[] encrypt(String message, byte[] key) {
        String token = Fernet.encrypt(key, message.getBytes(StandardCharsets.UTF_8));
        return token.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Decrypts a Fernet-encrypted message.
     *
     * @param encryptedMessage The encrypted message as a byte array (UTF-8 encoded base64 string).
     * @param key              The 32-byte Fernet key.
     * @return The decrypted message as a byte array.
     */
    public static byte[] decrypt(byte[] encryptedMessage, byte[] key) {
        String token = new String(encryptedMessage, StandardCharsets.UTF_8);
        return Fernet.decrypt(key, token);
    }
}