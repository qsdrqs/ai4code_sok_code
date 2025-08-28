import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * This class provides methods to encrypt and decrypt data using AES/GCM,
 * which is the standard Java equivalent for authenticated encryption like AES/EAX.
 */
public class SymmetricEncryption {

    // Use AES/GCM, the standard for authenticated encryption in Java.
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    // GCM recommended IV size is 12 bytes (96 bits).
    private static final int IV_SIZE_BYTES = 12;
    // GCM recommended tag size is 16 bytes (128 bits).
    private static final int TAG_SIZE_BITS = 128;

    /**
     * A simple container class to hold the IV and the ciphertext,
     * replacing the Python tuple (nonce, tag, ciphertext).
     * In GCM, the tag is appended to the ciphertext, so we only need two fields.
     */
    public static class CiphertextContainer {
        private final byte[] iv; // Corresponds to the 'nonce' in the Python code
        private final byte[] ciphertext; // Corresponds to 'ciphertext' + 'tag'

        public CiphertextContainer(byte[] iv, byte[] ciphertext) {
            this.iv = iv;
            this.ciphertext = ciphertext;
        }

        public byte[] getIv() {
            return iv;
        }

        public byte[] getCiphertext() {
            return ciphertext;
        }
    }

    /**
     * Encrypts a given plaintext using a given symmetric key.
     *
     * @param key       The symmetric key (must be 16, 24, or 32 bytes for AES).
     * @param plaintext The data to encrypt.
     * @return A container object with the IV (nonce) and the ciphertext (which includes the auth tag).
     * @throws Exception if encryption fails.
     */
    public static CiphertextContainer encrypt(SecretKey key, byte[] plaintext) throws Exception {
        // 1. Generate a random IV (Initialization Vector), which is the nonce.
        byte[] iv = new byte[IV_SIZE_BYTES];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        // 2. Initialize the Cipher for encryption
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_SIZE_BITS, iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmParameterSpec);

        // 3. Encrypt the data. The GCM implementation will append the authentication tag automatically.
        byte[] ciphertext = cipher.doFinal(plaintext);

        // 4. Return the IV and the ciphertext in a container object.
        return new CiphertextContainer(iv, ciphertext);
    }

    /**
     * Decrypts a given ciphertext using a given symmetric key.
     *
     * @param key                The symmetric key used for encryption.
     * @param ciphertextContainer The container holding the IV (nonce) and ciphertext.
     * @return The original decrypted data.
     * @throws Exception if decryption or tag verification fails.
     */
    public static byte[] decrypt(SecretKey key, CiphertextContainer ciphertextContainer) throws Exception {
        // 1. Get the IV and ciphertext from the container.
        byte[] iv = ciphertextContainer.getIv();
        byte[] ciphertext = ciphertextContainer.getCiphertext();

        // 2. Initialize the Cipher for decryption
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_SIZE_BITS, iv);
        cipher.init(Cipher.DECRYPT_MODE, key, gcmParameterSpec);

        // 3. Decrypt the data. The GCM implementation will automatically verify the
        // authentication tag. If the tag is invalid, it will throw an AEADBadTagException.
        byte[] decryptedData = cipher.doFinal(ciphertext);

        // 4. Return the original plaintext.
        return decryptedData;
    }

    // --- Main method for demonstration ---
    public static void main(String[] args) {
        try {
            // 1. Generate a secure AES key (256-bit)
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256); // AES-256
            SecretKey key = keyGenerator.generateKey();

            // You can also use a key from a known byte array:
            // byte[] keyBytes = new byte[32]; // Must be 32 bytes for AES-256
            // new SecureRandom().nextBytes(keyBytes);
            // SecretKey key = new SecretKeySpec(keyBytes, "AES");

            String originalString = "This is a secret message that needs to be encrypted.";
            byte[] plaintext = originalString.getBytes(StandardCharsets.UTF_8);

            System.out.println("Original Plaintext: " + originalString);
            System.out.println("Key (Base64): " + Base64.getEncoder().encodeToString(key.getEncoded()));
            System.out.println("--------------------------------------------------");

            // 2. Encrypt the data
            CiphertextContainer encryptedData = encrypt(key, plaintext);
            System.out.println("Encryption successful!");
            System.out.println("IV (Nonce) (Base64): " + Base64.getEncoder().encodeToString(encryptedData.getIv()));
            System.out.println("Ciphertext (with Tag) (Base64): " + Base64.getEncoder().encodeToString(encryptedData.getCiphertext()));
            System.out.println("--------------------------------------------------");


            // 3. Decrypt the data
            byte[] decryptedBytes = decrypt(key, encryptedData);
            String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);

            System.out.println("Decryption successful!");
            System.out.println("Decrypted Plaintext: " + decryptedString);
            System.out.println("--------------------------------------------------");

            // 4. Verification
            if (originalString.equals(decryptedString)) {
                System.out.println("SUCCESS: The decrypted text matches the original text.");
            } else {
                System.out.println("FAILURE: The decrypted text does NOT match the original text.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the cryptographic operation.");
            e.printStackTrace();
        }
    }
}