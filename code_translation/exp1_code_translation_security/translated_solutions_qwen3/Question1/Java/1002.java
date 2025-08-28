import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AESExample {

    /**
     * Encrypts the given data using AES in CBC mode.
     *
     * @param data The plaintext data to encrypt.
     * @param key  The AES key (must be 16, 24, or 32 bytes for AES-128, AES-192, or AES-256).
     * @param iv   The initialization vector (must be 16 bytes for AES).
     * @return The encrypted ciphertext.
     * @throws Exception If an error occurs during encryption.
     */
    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(data);
    }

    /**
     * Decrypts the given ciphertext using AES in CBC mode.
     *
     * @param data The ciphertext to decrypt.
     * @param key  The AES key (must be 16, 24, or 32 bytes for AES-128, AES-192, or AES-256).
     * @param iv   The initialization vector (must be 16 bytes for AES).
     * @return The decrypted plaintext.
     * @throws Exception If an error occurs during decryption.
     */
    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(data);
    }

    /**
     * Test function to verify that encryption and decryption work correctly.
     * It encrypts a known string, decrypts it, and asserts that the result matches the original.
     *
     * @throws Exception If an error occurs during the test.
     */
    public static void test() throws Exception {
        String plainText = "a secret message";
        byte[] data = plainText.getBytes(StandardCharsets.UTF_8);

        // Generate a 256-bit (32-byte) AES key
        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);

        // Generate a 128-bit (16-byte) IV
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);

        // Encrypt and decrypt
        byte[] encrypted = encrypt(data, key, iv);
        byte[] decrypted = decrypt(encrypted, key, iv);

        // Assert that the decrypted data matches the original
        if (!Arrays.equals(decrypted, data)) {
            throw new AssertionError("Decryption failed: decrypted data does not match original");
        }
    }

    /**
     * Main method to run the test.
     *
     * @param args Command-line arguments (not used).
     * @throws Exception If an error occurs during execution.
     */
    public static void main(String[] args) throws Exception {
        test();
        System.out.println("Test passed: encryption and decryption are working correctly.");
    }
}