import org.jfernet.Fernet; // The Java equivalent of cryptography.fernet.Fernet
import java.nio.charset.StandardCharsets;

/**
 * A Java translation of the Python Fernet encryption example.
 * This class demonstrates symmetric encryption and decryption using the
 * jfernet library, which implements the Fernet specification.
 */
public class FernetTranslation {

    /**
     * Encrypts a byte array using a Fernet key.
     *
     * @param input The byte array to encrypt (e.g., a message).
     * @param key   The URL-safe base64-encoded encryption key.
     * @return The encrypted byte array (token).
     */
    public static byte[] encrypt(byte[] input, String key) {
        // In Python: f = Fernet(key)
        Fernet f = new Fernet(key);
        // In Python: return f.encrypt(input)
        return f.encrypt(input);
    }

    /**
     * Decrypts a Fernet token using the corresponding key.
     *
     * @param input The encrypted byte array (token) to decrypt.
     * @param key   The URL-safe base64-encoded encryption key.
     * @return The original decrypted byte array.
     * @throws Exception if decryption fails (e.g., invalid key, corrupted token).
     */
    public static byte[] decrypt(byte[] input, String key) throws Exception {
        // In Python: f = Fernet(key)
        Fernet f = new Fernet(key);
        // In Python: return f.decrypt(input)
        return f.decrypt(input);
    }

    /**
     * The main method to run the encryption/decryption demonstration.
     */
    public static void main(String[] args) {
        try {
            // In Python: key = Fernet.generate_key()
            // The jfernet library provides a similar static method to generate a key.
            // The key is a URL-safe base64-encoded string.
            String key = Fernet.generateKey();

            // In Python: input = b'super secret'
            // In Java, we get bytes from a String, specifying the character set.
            byte[] input = "super secret".getBytes(StandardCharsets.UTF_8);

            // In Python: encryptedInput = encrypt(input, key)
            byte[] encryptedInput = encrypt(input, key);

            // In Python: decryptedInput = decrypt(encryptedInput, key)
            byte[] decryptedInput = decrypt(encryptedInput, key);

            // In Python: print(input) and print(decryptedInput)
            // To print the content of a byte array in Java, we convert it back to a String.
            System.out.println("Original Message: " + new String(input, StandardCharsets.UTF_8));
            System.out.println("Decrypted Message: " + new String(decryptedInput, StandardCharsets.UTF_8));

        } catch (Exception e) {
            // Handle potential exceptions from the decrypt method.
            System.err.println("An error occurred during encryption or decryption.");
            e.printStackTrace();
        }
    }
}