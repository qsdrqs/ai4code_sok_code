import com.macasaet.fernet.Key;
import com.macasaet.fernet.Fernet;
import java.nio.charset.StandardCharsets;

public class FernetExample {

    public static void main(String[] args) {
        String message = "the solutions are here somewhere";
        System.out.println("Secret Message: " + message);

        // Generate a symmetric key
        Key key = Key.generate();

        // Create a Fernet instance with the generated key
        Fernet fernet = new Fernet(key);

        // Encrypt the message
        String encMessage = encrypt(message, fernet);
        System.out.println("Encrypted Message: " + encMessage);

        // Decrypt the message
        String decMessage = decrypt(encMessage, fernet);
        System.out.println("Decrypted Message: " + decMessage);
    }

    /**
     * Encrypts a given message using the provided Fernet instance.
     *
     * @param message the plaintext message to encrypt
     * @param fernet  the Fernet instance initialized with a key
     * @return the encrypted message as a Base64URL-encoded string
     */
    public static String encrypt(String message, Fernet fernet) {
        return fernet.encrypt(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decrypts a given encrypted message using the provided Fernet instance.
     *
     * @param encMessage the encrypted message (Base64URL-encoded string)
     * @param fernet     the Fernet instance initialized with a key
     * @return the decrypted plaintext message
     */
    public static String decrypt(String encMessage, Fernet fernet) {
        byte[] decryptedBytes = fernet.decrypt(encMessage);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}