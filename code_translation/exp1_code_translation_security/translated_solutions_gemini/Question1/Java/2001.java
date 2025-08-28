import io.github.jopenlibs.fernet.Fernet;
import io.github.jopenlibs.fernet.FernetException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * A utility class for Fernet encryption and decryption, compatible with
 * Python's cryptography.fernet library.
 *
 * To use this code, you need to include the 'jd-fernet' dependency.
 * For Maven, add this to your pom.xml:
 * <dependency>
 *     <groupId>io.github.jopenlibs</groupId>
 *     <artifactId>jd-fernet</artifactId>
 *     <version>1.0.0</version>
 * </dependency>
 *
 * For Gradle, add this to your build.gradle:
 * implementation 'io.github.jopenlibs:jd-fernet:1.0.0'
 */
public class FernetUtils {

    /**
     * Function to encrypt a message with a key using Fernet (AES-128-CBC with HMAC).
     *
     * @param msg The plaintext message as a byte array.
     * @param key The 32-byte URL-safe base64-encoded encryption key.
     * @return The encrypted ciphertext as a byte array.
     */
    public static byte[] encrypt(byte[] msg, byte[] key) {
        Fernet fernet = Fernet.create(key);
        return fernet.encrypt(msg);
    }

    /**
     * Function to decrypt a message with a key using Fernet (AES-128-CBC with HMAC).
     *
     * @param ciphertext The encrypted ciphertext as a byte array.
     * @param key The 32-byte URL-safe base64-encoded encryption key.
     * @return The original decrypted message as a byte array.
     * @throws FernetException if the token is invalid (e.g., tampered with, expired, or incorrect key).
     */
    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws FernetException {
        Fernet fernet = Fernet.create(key);
        return fernet.decrypt(ciphertext);
    }

    /**
     * Main method to demonstrate the usage of the encryption and decryption functions.
     */
    public static void main(String[] args) {
        try {
            // 1. Generate a Fernet key (this is equivalent to Python's Fernet.generate_key())
            // A Fernet key is a URL-safe base64-encoded 32-byte sequence.
            byte[] key = Fernet.generateKey();
            System.out.println("Generated Key: " + new String(key, StandardCharsets.UTF_8));

            // 2. Define the message to be encrypted
            String originalMessage = "This is a secret message.";
            byte[] messageBytes = originalMessage.getBytes(StandardCharsets.UTF_8);
            System.out.println("Original Message: " + originalMessage);

            // 3. Encrypt the message
            byte[] encryptedToken = encrypt(messageBytes, key);
            System.out.println("Encrypted Token: " + new String(encryptedToken, StandardCharsets.UTF_8));

            // 4. Decrypt the message
            byte[] decryptedBytes = decrypt(encryptedToken, key);
            String decryptedMessage = new String(decryptedBytes, StandardCharsets.UTF_8);
            System.out.println("Decrypted Message: " + decryptedMessage);

            // 5. Verify that the original and decrypted messages are the same
            if (originalMessage.equals(decryptedMessage)) {
                System.out.println("\nSuccess: The decrypted message matches the original.");
            } else {
                System.out.println("\nFailure: The messages do not match.");
            }

        } catch (FernetException e) {
            System.err.println("Decryption failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}