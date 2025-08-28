import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Arrays;

/**
 * A Java class that provides AES/CBC/PKCS5Padding encryption and decryption,
 * mirroring the functionality of the provided Python script.
 */
public class AesCipher {

    // AES specification for the cipher
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    
    // The IV size for AES is always 16 bytes (128 bits)
    private static final int IV_LENGTH_BYTES = 16;

    /**
     * Encrypts a plaintext string using a given key.
     * The process is as follows:
     * 1. Generate a random 16-byte Initialization Vector (IV).
     * 2. Initialize the AES/CBC/PKCS5Padding cipher in ENCRYPT_MODE.
     * 3. Encrypt the plaintext (padding is handled automatically by PKCS5Padding).
     * 4. Prepend the IV to the ciphertext.
     * 5. Base64-encode the combined [IV + Ciphertext].
     *
     * @param key       The secret key as a byte array. Must be 16, 24, or 32 bytes for AES-128, AES-192, or AES-256.
     * @param plaintext The string to encrypt.
     * @return A Base64-encoded string containing the IV and the ciphertext.
     * @throws Exception if any cryptographic error occurs.
     */
    public static String encrypt(byte[] key, String plaintext) throws Exception {
        // 1. Generate a random Initialization Vector (IV)
        byte[] iv = new byte[IV_LENGTH_BYTES];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Create a SecretKeySpec from the key bytes
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);

        // 2. Initialize the cipher for encryption
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        // 3. Encrypt the plaintext
        byte[] ciphertext = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));

        // 4. Prepend the IV to the ciphertext
        byte[] combined = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(ciphertext, 0, combined, iv.length, ciphertext.length);

        // 5. Base64-encode the result
        return Base64.getEncoder().encodeToString(combined);
    }

    /**
     * Decrypts a Base64-encoded ciphertext using a given key.
     * The process is as follows:
     * 1. Base64-decode the incoming ciphertext string.
     * 2. Extract the 16-byte IV from the beginning of the decoded data.
     * 3. The rest of the data is the actual ciphertext.
     * 4. Initialize the AES/CBC/PKCS5Padding cipher in DECRYPT_MODE.
     * 5. Decrypt the ciphertext (unpadding is handled automatically).
     *
     * @param key              The secret key as a byte array. Must be the same key used for encryption.
     * @param base64Ciphertext The Base64-encoded string to decrypt.
     * @return The original plaintext string.
     * @throws Exception if any cryptographic error occurs.
     */
    public static String decrypt(byte[] key, String base64Ciphertext) throws Exception {
        // 1. Base64-decode the ciphertext
        byte[] combined = Base64.getDecoder().decode(base64Ciphertext);

        // 2. Extract the IV
        if (combined.length < IV_LENGTH_BYTES) {
            throw new IllegalArgumentException("Invalid ciphertext: length is less than IV size.");
        }
        IvParameterSpec ivParameterSpec = new IvParameterSpec(combined, 0, IV_LENGTH_BYTES);

        // 3. Extract the actual ciphertext
        byte[] ciphertext = Arrays.copyOfRange(combined, IV_LENGTH_BYTES, combined.length);

        // Create a SecretKeySpec from the key bytes
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ALGORITHM);

        // 4. Initialize the cipher for decryption
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        // 5. Decrypt the ciphertext
        byte[] decryptedBytes = cipher.doFinal(ciphertext);

        // Convert the decrypted bytes back to a string
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Main method for demonstration purposes.
     */
    public static void main(String[] args) {
        try {
            // Use a 256-bit key (32 bytes)
            // In a real application, this key should be securely stored and managed.
            SecureRandom random = new SecureRandom();
            byte[] key = new byte[32]; // AES-256
            random.nextBytes(key);

            String originalPlaintext = "This is a secret message for translation!";
            System.out.println("Original Text: " + originalPlaintext);
            System.out.println("Using Key (Hex): " + bytesToHex(key));
            System.out.println("------------------------------------");

            // Encrypt the text
            String encryptedText = encrypt(key, originalPlaintext);
            System.out.println("Encrypted (Base64): " + encryptedText);

            // Decrypt the text
            String decryptedText = decrypt(key, encryptedText);
            System.out.println("Decrypted Text: " + decryptedText);
            System.out.println("------------------------------------");

            // Verify the result
            System.out.println("Success: " + originalPlaintext.equals(decryptedText));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to display byte arrays as hex strings for demonstration
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}