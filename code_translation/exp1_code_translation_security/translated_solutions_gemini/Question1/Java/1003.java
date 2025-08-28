import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;

/**
 * A utility class for AES encryption and decryption using ECB mode.
 * This is a direct translation of the provided Python code.
 *
 * NOTE: AES/ECB mode is generally considered insecure for most use cases
 * because it does not use an Initialization Vector (IV), and identical
 * blocks of plaintext will be encrypted into identical blocks of ciphertext,
 * revealing patterns in the data. It is recommended to use a more secure
 * mode like CBC or GCM with proper padding and a random IV.
 */
public class AesEcbUtils {

    private static final String ALGORITHM = "AES";
    // The transformation string "AES/ECB/NoPadding" is the Java equivalent of
    // Python's AES.new(key, AES.MODE_ECB).
    // "NoPadding" means the input data must be a multiple of the block size (16 bytes).
    private static final String TRANSFORMATION = "AES/ECB/NoPadding";

    /**
     * Encrypts a byte array using AES/ECB/NoPadding.
     *
     * @param data The plaintext data to encrypt. Must be a multiple of 16 bytes.
     * @param key  The encryption key. Must be 16, 24, or 32 bytes long.
     * @return The encrypted ciphertext as a byte array.
     * @throws RuntimeException if encryption fails.
     */
    public static byte[] encryptBytesAes(byte[] data, byte[] key) {
        try {
            // Create a SecretKeySpec from the raw key bytes
            Key secretKey = new SecretKeySpec(key, ALGORITHM);

            // Get a Cipher instance for the specified transformation
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);

            // Initialize the cipher for encryption mode
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Perform the encryption
            return cipher.doFinal(data);
        } catch (Exception e) {
            // Wrap checked exceptions in a RuntimeException for simplicity
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    /**
     * Decrypts a byte array using AES/ECB/NoPadding.
     *
     * @param data The ciphertext data to decrypt. Must be a multiple of 16 bytes.
     * @param key  The decryption key. Must be 16, 24, or 32 bytes long.
     * @return The decrypted plaintext as a byte array.
     * @throws RuntimeException if decryption fails.
     */
    public static byte[] decryptBytesAes(byte[] data, byte[] key) {
        try {
            // Create a SecretKeySpec from the raw key bytes
            Key secretKey = new SecretKeySpec(key, ALGORITHM);

            // Get a Cipher instance for the specified transformation
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);

            // Initialize the cipher for decryption mode
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Perform the decryption
            return cipher.doFinal(data);
        } catch (Exception e) {
            // Wrap checked exceptions in a RuntimeException for simplicity
            throw new RuntimeException("Error decrypting data", e);
        }
    }

    /**
     * Main method to demonstrate the usage of the encryption and decryption functions.
     */
    public static void main(String[] args) {
        // A 16-byte (128-bit) key is required for AES-128.
        // Keys can also be 24 (192-bit) or 32 (256-bit) bytes long.
        String keyString = "ThisIsASecretKey"; // 16 characters = 16 bytes
        byte[] key = keyString.getBytes(StandardCharsets.UTF_8);

        // The data to be encrypted. For "NoPadding", its length must be a multiple of 16.
        // "1234567890123456" is 16 bytes. We'll use a 32-byte string.
        String originalString = "Two blocks of 16 bytes each....";
        byte[] originalData = originalString.getBytes(StandardCharsets.UTF_8);

        System.out.println("Original Data: " + originalString);
        System.out.println("Original Data (bytes): " + Arrays.toString(originalData));
        System.out.println("Key (bytes): " + Arrays.toString(key));
        System.out.println("-------------------------------------------");

        // Encrypt the data
        byte[] encryptedData = encryptBytesAes(originalData, key);
        System.out.println("Encrypted Data (Base64): " + Base64.getEncoder().encodeToString(encryptedData));
        System.out.println("Encrypted Data (bytes): " + Arrays.toString(encryptedData));
        System.out.println("-------------------------------------------");

        // Decrypt the data
        byte[] decryptedData = decryptBytesAes(encryptedData, key);
        String decryptedString = new String(decryptedData, StandardCharsets.UTF_8);
        System.out.println("Decrypted Data: " + decryptedString);
        System.out.println("Decrypted Data (bytes): " + Arrays.toString(decryptedData));
        System.out.println("-------------------------------------------");

        // Verify that the decrypted data matches the original
        System.out.println("Verification Successful: " + originalString.equals(decryptedString));
    }
}