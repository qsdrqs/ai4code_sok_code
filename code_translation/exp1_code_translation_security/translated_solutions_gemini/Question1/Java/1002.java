import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * This class demonstrates AES-CBC encryption and decryption, equivalent to the
 * provided Python script using the cryptography.io library.
 */
public class AesCbcExample {

    // The transformation string defines the algorithm, mode, and padding.
    // "AES/CBC/PKCS5Padding" is the standard way to specify AES with CBC mode in Java.
    // Python's cryptography library uses PKCS#7 padding by default, which is
    // functionally identical to PKCS#5 padding for AES's 128-bit block size.
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String ALGORITHM = "AES";

    /**
     * Encrypts data using AES/CBC/PKCS5Padding.
     *
     * @param data The plaintext data to encrypt.
     * @param key  The 256-bit (32-byte) encryption key.
     * @param iv   The 128-bit (16-byte) initialization vector.
     * @return The encrypted ciphertext.
     * @throws Exception if encryption fails.
     */
    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        // Wrap the raw key bytes in a SecretKeySpec
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        // Wrap the raw IV bytes in an IvParameterSpec
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Get a Cipher instance for the specified transformation
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        // Initialize the cipher for encryption mode
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        // Perform the encryption. doFinal() handles all data and adds padding.
        return cipher.doFinal(data);
    }

    /**
     * Decrypts data using AES/CBC/PKCS5Padding.
     *
     * @param data The ciphertext to decrypt.
     * @param key  The 256-bit (32-byte) decryption key.
     * @param iv   The 128-bit (16-byte) initialization vector.
     * @return The decrypted plaintext.
     * @throws Exception if decryption fails.
     */
    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        // Wrap the raw key bytes in a SecretKeySpec
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        // Wrap the raw IV bytes in an IvParameterSpec
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Get a Cipher instance for the specified transformation
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        // Initialize the cipher for decryption mode
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        // Perform the decryption. doFinal() handles all data and removes padding.
        return cipher.doFinal(data);
    }

    /**
     * A test function to verify that encryption and decryption work correctly.
     */
    public static void test() throws Exception {
        // The original message, converted to a byte array using UTF-8 encoding.
        String originalString = "a secret message";
        byte[] data = originalString.getBytes(StandardCharsets.UTF_8);

        // Generate a cryptographically secure random key and IV.
        // SecureRandom is the Java equivalent of Python's os.urandom().
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32]; // 256-bit key
        byte[] iv = new byte[16];  // 128-bit IV for CBC mode
        secureRandom.nextBytes(key);
        secureRandom.nextBytes(iv);

        System.out.println("Original data: \"" + originalString + "\"");
        System.out.println("Key (Hex): " + bytesToHex(key));
        System.out.println("IV (Hex):  " + bytesToHex(iv));

        // Encrypt the data
        byte[] encryptedData = encrypt(data, key, iv);
        System.out.println("Encrypted (Hex): " + bytesToHex(encryptedData));

        // Decrypt the data
        byte[] decryptedData = decrypt(encryptedData, key, iv);
        String decryptedString = new String(decryptedData, StandardCharsets.UTF_8);
        System.out.println("Decrypted data: \"" + decryptedString + "\"");

        // Assert that the decrypted data matches the original data.
        // In Java, Arrays.equals() must be used to compare the contents of byte arrays.
        if (Arrays.equals(data, decryptedData)) {
            System.out.println("\nTest PASSED: Decrypted data matches original data.");
        } else {
            System.err.println("\nTest FAILED: Decrypted data does not match original data.");
        }
    }

    /**
     * Main method to run the test.
     */
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            System.err.println("An error occurred during the test.");
            e.printStackTrace();
        }
    }

    /**
     * Helper utility to convert a byte array to a hexadecimal string for printing.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}