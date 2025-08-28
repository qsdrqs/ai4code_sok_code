import fernet.Token;
import java.nio.charset.StandardCharsets;

public class FernetUtil {

    /**
     * Encrypts a string using Fernet encryption.
     *
     * @param input The plaintext string to encrypt.
     * @param key   The Base64-encoded Fernet key.
     * @return The encrypted data as a byte array (Base64-encoded string).
     * @throws Exception If encryption fails.
     */
    public static byte[] encrypt(String input, String key) throws Exception {
        Token token = new Token();
        String encryptedString = token.encrypt(input, key);
        return encryptedString.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Decrypts a Fernet-encrypted byte array.
     *
     * @param input The encrypted byte array (Base64-encoded string).
     * @param key   The Base64-encoded Fernet key.
     * @return The decrypted plaintext string.
     * @throws Exception If decryption fails.
     */
    public static String decrypt(byte[] input, String key) throws Exception {
        Token token = new Token();
        String encryptedString = new String(input, StandardCharsets.UTF_8);
        return token.decrypt(encryptedString, key);
    }
}