import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * This class provides methods for AES-CTR encryption and decryption,
 * translating the functionality of the provided Python script which uses the pyaes library.
 */
public class AesCtr {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CTR/NoPadding";
    private static final int AES_BLOCK_SIZE_BYTES = 16;

    /**
     * Encrypts a string using AES in Counter (CTR) mode.
     *
     * NOTE: The Python pyaes.AESModeOfOperationCTR library uses a default counter
     * starting at 0. In JCA, this is equivalent to using a zero-filled
     * Initialization Vector (IV). This practice is insecure and is replicated here
     * only for a direct translation. In a production environment, a unique and
     * random IV must be generated for each encryption operation.
     *
     * @param keyBytes 256-bit (32-byte) key, UTF-8 encoded.
     * @param message  The string message to encrypt.
     * @return The encrypted ciphertext as a byte array.
     * @throws Exception if an error occurs during encryption.
     */
    public static byte[] encrypt(byte[] keyBytes, String message) throws Exception {
        // Create a SecretKeySpec from the raw key bytes.
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);

        // Create a zero-filled IV to match pyaes's default behavior.
        // **SECURITY WARNING**: This is not secure. A unique IV should be used for each message.
        byte[] iv = new byte[AES_BLOCK_SIZE_BYTES];
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Get a Cipher instance and initialize it for encryption.
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Encrypt the message.
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decrypts a ciphertext using AES in Counter (CTR) mode.
     *
     * @param keyBytes   The same 256-bit (32-byte) key used for encryption.
     * @param ciphertext The encrypted byte array.
     * @return The decrypted plaintext as a UTF-8 string.
     * @throws Exception if an error occurs during decryption.
     */
    public static String decrypt(byte[] keyBytes, byte[] ciphertext) throws Exception {
        // Create a SecretKeySpec from the raw key bytes.
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);

        // The IV must be the same as the one used for encryption.
        byte[] iv = new byte[AES_BLOCK_SIZE_BYTES];
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Get a Cipher instance and initialize it for decryption.
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        // Decrypt the ciphertext.
        byte[] decryptedBytes = cipher.doFinal(ciphertext);

        // Convert the decrypted bytes back to a string.
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Runs a round-trip test to verify encryption and decryption.
     */
    public static void test() {
        try {
            // The key must be 32 bytes for AES-256.
            byte[] key = "This_key_for_demo_purposes_only!".getBytes(StandardCharsets.UTF_8);
            String originalText = "Text may be any length you wish, no padding is required";

            // Encrypt the text.
            byte[] ciphertext = encrypt(key, originalText);

            // Decrypt the text and print it to the console.
            String decryptedText = decrypt(key, ciphertext);
            System.out.println(decryptedText);

        } catch (Exception e) {
            System.err.println("An error occurred during the test:");
            e.printStackTrace();
        }
    }

    /**
     * Main method to execute the test.
     */
    public static void main(String[] args) {
        test();
    }
}