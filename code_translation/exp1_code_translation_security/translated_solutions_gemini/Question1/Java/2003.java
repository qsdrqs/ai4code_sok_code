import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;

/**
 * This class provides methods to encrypt and decrypt data using AES/ECB.
 * It is a Java translation of the provided Python script.
 *
 * Note: The ECB (Electronic Codebook) mode is insecure and should not be used
 * for most real-world applications as it does not provide serious message confidentiality.
 * This example is for demonstration purposes only.
 */
public class AesEcbExample {

    /**
     * Encodes a byte array using an AES key in ECB mode with no padding.
     *
     * @param inp The byte array to encrypt. Its length must be a multiple of 16.
     * @param key The AES key. Must be 16, 24, or 32 bytes long.
     * @return The encrypted byte array.
     */
    public static byte[] encode(byte[] inp, byte[] key) {
        try {
            // Create a SecretKeySpec from the raw key bytes
            Key aesKey = new SecretKeySpec(key, "AES");

            // Get a Cipher instance for AES with ECB mode and no padding.
            // This configuration matches the behavior of the Python Crypto library example.
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");

            // Initialize the cipher for encryption
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);

            // Perform the encryption
            return cipher.doFinal(inp);
        } catch (Exception e) {
            // For simplicity, wrap checked exceptions in a RuntimeException
            throw new RuntimeException("Error during encryption", e);
        }
    }

    /**
     * Decodes a byte array using an AES key in ECB mode with no padding.
     *
     * @param inp The encrypted byte array to decrypt.
     * @param key The AES key used for encryption.
     * @return The original, decrypted byte array.
     */
    public static byte[] decode(byte[] inp, byte[] key) {
        try {
            // Create a SecretKeySpec from the raw key bytes
            Key aesKey = new SecretKeySpec(key, "AES");

            // Get a Cipher instance for AES with ECB mode and no padding
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");

            // Initialize the cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, aesKey);

            // Perform the decryption
            return cipher.doFinal(inp);
        } catch (Exception e) {
            // For simplicity, wrap checked exceptions in a RuntimeException
            throw new RuntimeException("Error during decryption", e);
        }
    }

    public static void main(String[] args) {
        // In Python, b"..." creates a byte string. In Java, we get bytes from a String.
        // The input and key are both 16 bytes, which is a valid AES block and key size.
        byte[] inp = "1111111111111111".getBytes(StandardCharsets.UTF_8);
        byte[] key = "1111111111111111".getBytes(StandardCharsets.UTF_8);

        // Encrypt the input
        byte[] encoded = encode(inp, key);

        // Decrypt the result
        byte[] decoded = decode(encoded, key);

        // The Python 'assert' is equivalent to this check in Java.
        // We use Arrays.equals() to compare the contents of byte arrays.
        assert Arrays.equals(decoded, inp);

        // Print the results to verify
        System.out.println("Original input:  " + new String(inp, StandardCharsets.UTF_8));
        System.out.println("Decoded output:  " + new String(decoded, StandardCharsets.UTF_8));
        System.out.println("Assertion Passed: " + Arrays.equals(decoded, inp));
    }
}