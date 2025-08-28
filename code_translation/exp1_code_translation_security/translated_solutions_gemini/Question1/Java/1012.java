import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;
import com.macasaet.fernet.Validator;
import com.macasaet.fernet.StringValidator;
import java.nio.charset.StandardCharsets;

/**
 * A Java class that provides methods to encrypt and decrypt text using the Fernet
 * specification, similar to the Python cryptography.fernet library.
 */
public class TextEncryptor {

    // The secret key. In a real application, this should be loaded securely
    // from a configuration file or environment variable, not generated on the fly.
    // It is static and final to ensure it's created only once for the class.
    private static final Key key = Key.generateKey();

    /**
     * Encrypts a plain text string.
     *
     * @param plainText The string to encrypt.
     * @return A Fernet token string (Base64-encoded encrypted text).
     */
    public static String encryptText(String plainText) {
        // The Token.generate method handles the conversion of the string to bytes using UTF-8
        final Token token = Token.generate(key, plainText);
        return token.serialise();
    }

    /**
     * Decrypts a Fernet token string.
     *
     * Note: The original Python code had a bug in decrypt_text where it tried to
     * decrypt `plain_text` instead of `encrypted_text`. This Java version corrects that logic.
     *
     * @param encryptedText The Fernet token string to decrypt.
     * @return The original plain text string.
     */
    public static String decryptText(String encryptedText) {
        // The StringValidator handles validating the token and decrypting the payload to a String.
        // It assumes the payload is a UTF-8 encoded string, which matches the Python default.
        final Validator<String> validator = new StringValidator();
        
        // The Token.fromString method parses the serialized token.
        final Token token = Token.fromString(encryptedText);

        // The validator.validateAndDecrypt method checks the token's validity
        // (timestamp, signature, etc.) and returns the decrypted payload.
        return validator.validateAndDecrypt(key, token);
    }

    // Main method to demonstrate the encryption and decryption process.
    public static void main(String[] args) {
        String originalText = "This is a secret message.";

        System.out.println("Original Text: " + originalText);
        System.out.println("Key (Base64): " + key.serialise());

        // Encrypt the text
        String encrypted = encryptText(originalText);
        System.out.println("Encrypted Text: " + encrypted);

        // Decrypt the text
        try {
            String decrypted = decryptText(encrypted);
            System.out.println("Decrypted Text: " + decrypted);
        } catch (Exception e) {
            // Fernet tokens can be invalid (e.g., bad signature, expired TTL)
            System.err.println("Decryption failed: " + e.getMessage());
        }
    }
}