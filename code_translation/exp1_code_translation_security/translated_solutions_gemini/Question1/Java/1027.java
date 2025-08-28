import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * This class provides methods to encrypt and decrypt messages using AES in CBC mode
 * with PKCS5Padding. The behavior is designed to be compatible with the provided
 * Python script which uses pycryptodome.
 */
public class AesCbcEncryption {

    // AES block size is 16 bytes (128 bits)
    private static final int AES_BLOCK_SIZE = 16;
    // The transformation string specifies the algorithm, mode, and padding.
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String ALGORITHM = "AES";

    /**
     * Encrypts a message using a given key.
     *
     * @param message The plaintext message to encrypt (String).
     * @param key     The encryption key (must be 16, 24, or 32 bytes for AES-128, AES-192, or AES-256).
     * @return The ciphertext, with the IV prepended to it.
     * @throws Exception if any cryptographic error occurs.
     */
    public static byte[] encrypt(String message, byte[] key) throws Exception {
        // 1. Create a new SecretKeySpec for the given key.
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);

        // 2. Get a Cipher instance for AES/CBC/PKCS5Padding.
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);

        // 3. Generate a random IV (Initialization Vector).
        // The IV size for CBC must be the same as the block size (16 bytes for AES).
        byte[] iv = new byte[AES_BLOCK_SIZE];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // 4. Initialize the cipher for encryption mode with the key and IV.
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        // 5. Encrypt the message.
        // The message is first converted to bytes using UTF-8.
        // doFinal() handles the padding automatically.
        byte[] encryptedMessage = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));

        // 6. Prepend the IV to the ciphertext.
        // This is crucial for the decrypt function to retrieve the same IV.
        byte[] ciphertext = new byte[iv.length + encryptedMessage.length];
        System.arraycopy(iv, 0, ciphertext, 0, iv.length);
        System.arraycopy(encryptedMessage, 0, ciphertext, iv.length, encryptedMessage.length);

        return ciphertext;
    }

    /**
     * Decrypts a ciphertext using a given key.
     *
     * @param ciphertext The ciphertext to decrypt (byte array), which must include the prepended IV.
     * @param key        The decryption key (must be the same key used for encryption).
     * @return The original plaintext message (String).
     * @throws Exception if any cryptographic error occurs.
     */
    public static String decrypt(byte[] ciphertext, byte[] key) throws Exception {
        // 1. Create a new SecretKeySpec for the given key.
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);

        // 2. Get a Cipher instance for AES/CBC/PKCS5Padding.
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);

        // 3. Extract the IV from the beginning of the ciphertext.
        byte[] iv = Arrays.copyOfRange(ciphertext, 0, AES_BLOCK_SIZE);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // 4. Extract the actual encrypted message (everything after the IV).
        byte[] encryptedMessage = Arrays.copyOfRange(ciphertext, AES_BLOCK_SIZE, ciphertext.length);

        // 5. Initialize the cipher for decryption mode with the key and IV.
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        // 6. Decrypt the message.
        // doFinal() handles the unpadding automatically.
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage);

        // 7. Convert the decrypted bytes back to a string using UTF-8.
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Main method for demonstration purposes.
     */
    public static void main(String[] args) {
        try {
            // Use a 16-byte key for AES-128
            String keyString = "this-is-a-16-byte-key-for-aes!"; // Must be 16, 24, or 32 chars
            byte[] key = Arrays.copyOf(keyString.getBytes(StandardCharsets.UTF_8), 16);

            String originalMessage = "This is a secret message for demonstration.";

            System.out.println("Original Message: " + originalMessage);
            System.out.println("Key (bytes): " + Arrays.toString(key));
            System.out.println("--------------------------------------------------");

            // Encrypt the message
            byte[] ciphertext = encrypt(originalMessage, key);
            System.out.println("Encrypted (Ciphertext): " + Arrays.toString(ciphertext));
            System.out.println("Ciphertext length: " + ciphertext.length);
            System.out.println("--------------------------------------------------");


            // Decrypt the message
            String decryptedMessage = decrypt(ciphertext, key);
            System.out.println("Decrypted Message: " + decryptedMessage);
            System.out.println("--------------------------------------------------");


            // Verify that the original and decrypted messages are the same
            System.out.println("Verification successful: " + originalMessage.equals(decryptedMessage));

        } catch (Exception e) {
            System.err.println("An error occurred during encryption/decryption.");
            e.printStackTrace();
        }
    }
}