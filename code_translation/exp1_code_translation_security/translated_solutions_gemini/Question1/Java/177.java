import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * This class provides methods to encrypt and decrypt data using AES/GCM,
 * which is the Java standard equivalent of the AES/EAX mode used in the Python example.
 */
public class AesGcmEncryptor {

    // Use AES algorithm
    private static final String ALGORITHM = "AES";
    // Use GCM mode, which provides authenticated encryption (confidentiality and integrity)
    private static final String CIPHER_TRANSFORMATION = "AES/GCM/NoPadding";
    // GCM recommended IV size is 12 bytes (96 bits)
    private static final int IV_SIZE_BYTES = 12;
    // GCM recommended tag size is 16 bytes (128 bits)
    private static final int TAG_SIZE_BITS = 128;

    /**
     * A simple container class to hold the encrypted data (ciphertext + auth tag) and the IV.
     * This is the Java equivalent of the Python tuple (c, nonce).
     */
    public static class EncryptedPayload {
        private final byte[] ciphertext;
        private final byte[] iv;

        public EncryptedPayload(byte[] ciphertext, byte[] iv) {
            this.ciphertext = ciphertext;
            this.iv = iv;
        }

        public byte[] getCiphertext() {
            return ciphertext;
        }

        public byte[] getIv() {
            return iv;
        }
    }

    /**
     * Encrypts a message using a secret key.
     *
     * @param messageBytes The plaintext message to encrypt as a byte array.
     * @param secretKeyBytes The secret key as a byte array (must be 16, 24, or 32 bytes for AES).
     * @return An EncryptedPayload object containing the ciphertext and the initialization vector (IV/nonce).
     * @throws Exception if encryption fails.
     */
    public static EncryptedPayload encrypt(byte[] messageBytes, byte[] secretKeyBytes) throws Exception {
        // 1. Create a secret key spec from the raw key bytes
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, ALGORITHM);

        // 2. Generate a random Initialization Vector (IV), also known as a nonce
        byte[] iv = new byte[IV_SIZE_BYTES];
        new SecureRandom().nextBytes(iv);

        // 3. Get a Cipher instance for AES/GCM
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);

        // 4. Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_SIZE_BITS, iv);

        // 5. Initialize the cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

        // 6. Encrypt the data. The result includes the ciphertext and the authentication tag.
        byte[] ciphertext = cipher.doFinal(messageBytes);

        // 7. Return the IV and the ciphertext in a container object
        return new EncryptedPayload(ciphertext, iv);
    }

    /**
     * Decrypts an encrypted payload using a secret key.
     *
     * @param payload The EncryptedPayload object containing the ciphertext and IV.
     * @param secretKeyBytes The secret key used for encryption.
     * @return The original decrypted message as a byte array.
     * @throws Exception if decryption fails (e.g., wrong key, tampered data).
     */
    public static byte[] decrypt(EncryptedPayload payload, byte[] secretKeyBytes) throws Exception {
        // 1. Create a secret key spec from the raw key bytes
        SecretKey secretKey = new SecretKeySpec(secretKeyBytes, ALGORITHM);

        // 2. Get a Cipher instance for AES/GCM
        Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION);

        // 3. Create GCMParameterSpec from the IV used during encryption
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_SIZE_BITS, payload.getIv());

        // 4. Initialize the cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

        // 5. Decrypt the data. This will also automatically verify the authentication tag.
        // If the tag is invalid, it will throw an AEADBadTagException.
        return cipher.doFinal(payload.getCiphertext());
    }

    // Main method to demonstrate the usage
    public static void main(String[] args) {
        try {
            // Use a 256-bit (32-byte) key for this example
            byte[] secretKey = new byte[32];
            new SecureRandom().nextBytes(secretKey); // In a real app, use a secure key management system

            String originalMessage = "This is a secret message that needs to be translated from Python to Java.";
            System.out.println("Original Message: " + originalMessage);
            System.out.println("Secret Key (Base64): " + Base64.getEncoder().encodeToString(secretKey));
            System.out.println("---");

            // Encrypt the message
            EncryptedPayload encryptedPayload = encrypt(originalMessage.getBytes(StandardCharsets.UTF_8), secretKey);
            System.out.println("Encrypted Ciphertext (Base64): " + Base64.getEncoder().encodeToString(encryptedPayload.getCiphertext()));
            System.out.println("IV/Nonce (Base64): " + Base64.getEncoder().encodeToString(encryptedPayload.getIv()));
            System.out.println("---");

            // Decrypt the message
            byte[] decryptedBytes = decrypt(encryptedPayload, secretKey);
            String decryptedMessage = new String(decryptedBytes, StandardCharsets.UTF_8);
            System.out.println("Decrypted Message: " + decryptedMessage);

            // Verify correctness
            if (originalMessage.equals(decryptedMessage)) {
                System.out.println("\nSuccess: The decrypted message matches the original.");
            } else {
                System.out.println("\nFailure: The decrypted message does NOT match the original.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the encryption/decryption process.");
            e.printStackTrace();
        }
    }
}