import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * This class provides a Java equivalent for the Python Fernet symmetric encryption example.
 * It uses AES/GCM/NoPadding, which is a standard, secure, and widely used
 * authenticated encryption algorithm available in the standard Java Cryptography Architecture (JCA).
 */
public class SymmetricEncryptionExample {

    // AES key size in bits. Fernet uses 128-bit keys for encryption.
    private static final int AES_KEY_SIZE = 128;
    // GCM Initialization Vector (IV) length in bytes. 12 bytes is recommended for GCM.
    private static final int GCM_IV_LENGTH = 12;
    // GCM Authentication Tag length in bits. 128 bits is a standard choice.
    private static final int GCM_TAG_LENGTH = 128;

    /**
     * Generates a new AES secret key.
     * This is equivalent to Python's `Fernet.generate_key()`.
     *
     * @return A new SecretKey for AES encryption.
     * @throws Exception if the AES algorithm is not available.
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(AES_KEY_SIZE);
        return keyGenerator.generateKey();
    }

    /**
     * Encrypts a plaintext string using a given AES key.
     *
     * @param plainText The string to encrypt.
     * @param key       The SecretKey to use for encryption.
     * @return A byte array containing the encrypted data (IV + ciphertext).
     * @throws Exception if encryption fails.
     */
    public static byte[] encrypt(String plainText, SecretKey key) throws Exception {
        // 1. Generate a random Initialization Vector (IV)
        byte[] iv = new byte[GCM_IV_LENGTH];
        new SecureRandom().nextBytes(iv);

        // 2. Get a Cipher instance for AES/GCM
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // 3. Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

        // 4. Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmParameterSpec);

        // 5. Encrypt the plaintext
        byte[] cipherText = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // 6. Prepend the IV to the ciphertext for use in decryption
        // This is a standard practice as the IV does not need to be secret.
        ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + cipherText.length);
        byteBuffer.put(iv);
        byteBuffer.put(cipherText);
        return byteBuffer.array();
    }

    /**
     * Decrypts a ciphertext byte array using a given AES key.
     *
     * @param cipherTextWithIv The byte array to decrypt (must include the IV prepended).
     * @param key              The SecretKey to use for decryption.
     * @return The original decrypted string.
     * @throws Exception if decryption fails (e.g., wrong key, tampered data).
     */
    public static String decrypt(byte[] cipherTextWithIv, SecretKey key) throws Exception {
        // 1. Wrap the incoming byte array to easily extract IV and ciphertext
        ByteBuffer byteBuffer = ByteBuffer.wrap(cipherTextWithIv);

        // 2. Extract the IV
        byte[] iv = new byte[GCM_IV_LENGTH];
        byteBuffer.get(iv);

        // 3. Extract the actual ciphertext
        byte[] cipherText = new byte[byteBuffer.remaining()];
        byteBuffer.get(cipherText);

        // 4. Get a Cipher instance for AES/GCM
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        // 5. Create GCMParameterSpec from the extracted IV
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, iv);

        // 6. Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, key, gcmParameterSpec);

        // 7. Decrypt the ciphertext
        byte[] plainTextBytes = cipher.doFinal(cipherText);

        // 8. Convert the decrypted bytes back to a string
        return new String(plainTextBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            // Generate a key, equivalent to `ex_key = Fernet.generate_key()`
            SecretKey exKey = generateKey();

            String originalMessage = "Hello World!";

            // Encrypt the message
            byte[] encrypted = encrypt(originalMessage, exKey);

            // In Python, printing bytes shows a representation like `b'...'`.
            // In Java, it's standard to Base64-encode byte arrays for printing or transmission.
            System.out.println("Encrypted (Base64): " + Base64.getEncoder().encodeToString(encrypted));

            // Decrypt the message
            String decrypted = decrypt(encrypted, exKey);
            System.out.println("Decrypted: " + decrypted);

        } catch (Exception e) {
            System.err.println("An error occurred during the cryptographic operation.");
            e.printStackTrace();
        }
    }
}