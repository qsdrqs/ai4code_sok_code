import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * This class provides methods to encrypt and decrypt strings in a way that is
 * compatible with the Python 'cryptocode' library.
 *
 * The 'cryptocode' library uses:
 * - AES/CBC/PKCS5Padding for encryption.
 * - A SHA-256 hash of the key to create a 256-bit encryption key.
 * - A random 16-byte Initialization Vector (IV) which is prepended to the ciphertext.
 * - Base64 encoding to create a final, transportable string.
 */
public class CryptoUtils {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int IV_LENGTH_BYTES = 16; // AES block size

    /**
     * Encrypts a plaintext string using a key, mimicking cryptocode.encrypt().
     *
     * @param plainText The string to encrypt.
     * @param key       The key (password) to use for encryption.
     * @return A Base64 encoded string containing the IV and the ciphertext.
     */
    public static String encodeStr(String plainText, String key) {
        try {
            // 1. Derive a 256-bit key from the user-provided key using SHA-256.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = digest.digest(key.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORITHM);

            // 2. Generate a random 16-byte Initialization Vector (IV).
            byte[] iv = new byte[IV_LENGTH_BYTES];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            // 3. Initialize the Cipher for encryption.
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

            // 4. Encrypt the plaintext.
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

            // 5. Combine IV and ciphertext. The IV is prepended to the ciphertext.
            byte[] combined = new byte[iv.length + encryptedBytes.length];
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);

            // 6. Base64 encode the combined byte array.
            return Base64.getEncoder().encodeToString(combined);

        } catch (Exception e) {
            // In a real application, handle this exception more gracefully.
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Decrypts a string that was encrypted with encodeStr, mimicking cryptocode.decrypt().
     *
     * @param strEncoded The Base64 encoded string to decrypt.
     * @param key        The key (password) that was used for encryption.
     * @return The original plaintext string.
     */
    public static String decodeStr(String strEncoded, String key) {
        try {
            // 1. Derive the same 256-bit key from the user-provided key.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = digest.digest(key.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORITHM);

            // 2. Base64 decode the incoming string.
            byte[] combined = Base64.getDecoder().decode(strEncoded);

            // 3. Extract the IV from the first 16 bytes.
            byte[] iv = Arrays.copyOfRange(combined, 0, IV_LENGTH_BYTES);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            // 4. Extract the ciphertext from the rest of the byte array.
            byte[] encryptedBytes = Arrays.copyOfRange(combined, IV_LENGTH_BYTES, combined.length);

            // 5. Initialize the Cipher for decryption.
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

            // 6. Decrypt the ciphertext.
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // 7. Convert the decrypted bytes back to a string.
            return new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            // If the key is wrong or the data is corrupt, decryption will fail.
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Main method to demonstrate the encryption and decryption process.
     */
    public static void main(String[] args) {
        String plainText = "I am okay";
        String key = "wow";

        System.out.println("Original Text: " + plainText);
        System.out.println("Key: " + key);
        System.out.println("------------------------------------");

        // Encode the string
        String strEncoded1 = encodeStr(plainText, key);
        System.out.println("Encoded String: " + strEncoded1);

        // Decode the string
        String decodedString = decodeStr(strEncoded1, key);
        System.out.println("Decoded String: " + decodedString);
    }
}