import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AESExample {

    /**
     * Encrypts the given input using AES in ECB mode with the provided key.
     * 
     * @param inp The input data to encrypt (must be a multiple of 16 bytes).
     * @param key The AES key (must be 16, 24, or 32 bytes long).
     * @return The encrypted data as a byte array.
     */
    public static byte[] encode(byte[] inp, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
            return cipher.doFinal(inp);
        } catch (Exception e) {
            throw new RuntimeException("Encryption error", e);
        }
    }

    /**
     * Decrypts the given input using AES in ECB mode with the provided key.
     * 
     * @param inp The encrypted data to decrypt (must be a multiple of 16 bytes).
     * @param key The AES key (must be 16, 24, or 32 bytes long).
     * @return The decrypted data as a byte array.
     */
    public static byte[] decode(byte[] inp, byte[] key) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"));
            return cipher.doFinal(inp);
        } catch (Exception e) {
            throw new RuntimeException("Decryption error", e);
        }
    }

    /**
     * Main method to test the AES ECB encryption and decryption.
     * It verifies that decrypting the encrypted data returns the original input.
     */
    public static void main(String[] args) {
        // Input and key are both 16 bytes of '1's
        byte[] inp = "1111111111111111".getBytes(StandardCharsets.UTF_8);
        byte[] key = inp.clone(); // Ensure key is a separate copy of the same bytes

        // Encrypt and decrypt
        byte[] encoded = encode(inp, key);
        byte[] decoded = decode(encoded, key);

        // Assert that the decoded result matches the original input
        if (!Arrays.equals(decoded, inp)) {
            throw new AssertionError("Test failed: Decrypted data does not match original input.");
        }

        System.out.println("Test passed: Decrypted data matches original input.");
    }
}