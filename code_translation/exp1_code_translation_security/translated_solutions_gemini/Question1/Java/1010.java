import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;

/**
 * This class provides methods for symmetric encryption and decryption using AES/GCM.
 * It is a Java translation of the provided Python script.
 */
public class SymmetricEncryption {

    // Define cryptographic constants
    private static final String ALGORITHM = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int KEY_SIZE_BITS = 128; // Corresponds to 16 bytes from os.urandom(16)
    private static final int GCM_IV_LENGTH_BYTES = 12; // Recommended nonce/IV length for GCM is 12 bytes
    private static final int GCM_TAG_LENGTH_BITS = 128; // Authentication tag length

    /**
     * A helper class to hold the result of an encryption operation,
     * containing both the ciphertext and the nonce (IV).
     */
    public static class EncryptionResult {
        public final byte[] ciphertext;
        public final byte[] nonce;

        public EncryptionResult(byte[] ciphertext, byte[] nonce) {
            this.ciphertext = ciphertext;
            this.nonce = nonce;
        }
    }

    /**
     * Function to generate a symmetric key.
     *
     * @return A SecretKey for AES encryption.
     * @throws GeneralSecurityException if key generation fails.
     */
    public static SecretKey generateKey() throws GeneralSecurityException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        // Initialize the key generator for 128-bit AES
        keyGenerator.init(KEY_SIZE_BITS);
        // Generate the key
        return keyGenerator.generateKey();
    }

    /**
     * Function to encrypt a string using a symmetric key.
     *
     * @param key  The SecretKey to use for encryption.
     * @param data The plaintext string to encrypt.
     * @return An EncryptionResult object containing the ciphertext and the nonce.
     * @throws GeneralSecurityException if encryption fails.
     */
    public static EncryptionResult encrypt(SecretKey key, String data) throws GeneralSecurityException {
        // Generate a random nonce (Initialization Vector)
        byte[] nonce = new byte[GCM_IV_LENGTH_BYTES];
        new SecureRandom().nextBytes(nonce);

        // Create a cipher object
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);

        // Create GCM parameter spec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH_BITS, nonce);

        // Initialize the cipher for encryption mode
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmParameterSpec);

        // Encrypt the data (and get the authentication tag appended)
        byte[] ciphertext = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

        // Return the ciphertext and the nonce
        return new EncryptionResult(ciphertext, nonce);
    }

    /**
     * Function to decrypt a string.
     *
     * @param key        The SecretKey used for the original encryption.
     * @param nonce      The nonce (IV) used for the original encryption.
     * @param ciphertext The encrypted data.
     * @return The decrypted plaintext string.
     * @throws GeneralSecurityException if decryption fails (e.g., bad key or tampered data).
     */
    public static String decrypt(SecretKey key, byte[] nonce, byte[] ciphertext) throws GeneralSecurityException {
        // Create a cipher object
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);

        // Create GCM parameter spec from the nonce
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH_BITS, nonce);

        // Initialize the cipher for decryption mode
        cipher.init(Cipher.DECRYPT_MODE, key, gcmParameterSpec);

        // Decrypt the data (which also verifies the authentication tag)
        byte[] decryptedData = cipher.doFinal(ciphertext);

        // Convert the decrypted bytes back to a string
        return new String(decryptedData, StandardCharsets.UTF_8);
    }

    /**
     * Example usage
     */
    public static void main(String[] args) {
        try {
            // 1. Generate a key
            SecretKey key = generateKey();
            System.out.println("Generated AES Key: (details hidden for security)");

            // 2. Define test data
            String testData = "Jim's test";
            System.out.println("Original Data: " + testData);

            // 3. Encrypt the data
            EncryptionResult encryptionResult = encrypt(key, testData);
            byte[] encrypted = encryptionResult.ciphertext;
            byte[] nonce = encryptionResult.nonce;
            System.out.println("Encrypted (Ciphertext): " + bytesToHex(encrypted));
            System.out.println("Nonce (IV): " + bytesToHex(nonce));

            // 4. Decrypt the data
            String restoredData = decrypt(key, nonce, encrypted);
            System.out.println("Restored Data: " + restoredData);

            // 5. Verify correctness
            if (testData.equals(restoredData)) {
                System.out.println("\nSuccess: The restored data matches the original data.");
            } else {
                System.out.println("\nFailure: The restored data does NOT match the original data.");
            }

        } catch (GeneralSecurityException e) {
            System.err.println("A cryptographic error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Helper method to convert a byte array to a hexadecimal string for display.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}