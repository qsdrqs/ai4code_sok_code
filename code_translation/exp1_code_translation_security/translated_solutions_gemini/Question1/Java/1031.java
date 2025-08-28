import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * A Java implementation of symmetric encryption and decryption,
 * functionally equivalent to the Python Fernet example.
 *
 * This implementation uses AES/GCM, a modern and secure authenticated encryption mode
 * available in the standard Java Cryptography Architecture (JCA).
 */
public class SymmetricEncryptionExample {

    // Use AES/GCM/NoPadding, an authenticated encryption mode.
    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    // GCM recommended IV size is 12 bytes (96 bits).
    private static final int IV_LENGTH_BYTE = 12;
    // GCM recommended authentication tag size is 16 bytes (128 bits).
    private static final int TAG_LENGTH_BIT = 128;

    /**
     * Encrypts a message using a symmetric key (AES/GCM).
     * The IV is prepended to the ciphertext.
     *
     * @param message The plaintext message to encrypt.
     * @param key     The SecretKey to use for encryption.
     * @return A byte array containing the IV and the ciphertext.
     * @throws Exception if encryption fails.
     */
    public static byte[] encrypt(String message, SecretKey key) throws Exception {
        // 1. Get Cipher instance
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        // 2. Create a random Initialization Vector (IV)
        byte[] iv = new byte[IV_LENGTH_BYTE];
        new SecureRandom().nextBytes(iv);

        // 3. Create GCMParameterSpec
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);

        // 4. Initialize Cipher for ENCRYPT_MODE
        cipher.init(Cipher.ENCRYPT_MODE, key, gcmParameterSpec);

        // 5. Encrypt the message
        byte[] cipherText = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));

        // 6. Prepend the IV to the ciphertext for use in decryption
        ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + cipherText.length);
        byteBuffer.put(iv);
        byteBuffer.put(cipherText);
        return byteBuffer.array();
    }

    /**
     * Decrypts a message using a symmetric key (AES/GCM).
     * Assumes the IV is prepended to the ciphertext.
     *
     * @param encMessage The byte array containing the IV and ciphertext.
     * @param key        The SecretKey to use for decryption.
     * @return The decrypted plaintext message.
     * @throws Exception if decryption fails (e.g., wrong key, tampered message).
     */
    public static String decrypt(byte[] encMessage, SecretKey key) throws Exception {
        // 1. Wrap the encrypted message in a ByteBuffer to easily extract IV and ciphertext
        ByteBuffer bb = ByteBuffer.wrap(encMessage);

        // 2. Extract the IV
        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);

        // 3. Extract the ciphertext
        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        // 4. Get Cipher instance
        Cipher cipher = Cipher.getInstance(ENCRYPT_ALGO);

        // 5. Create GCMParameterSpec from the extracted IV
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(TAG_LENGTH_BIT, iv);

        // 6. Initialize Cipher for DECRYPT_MODE
        cipher.init(Cipher.DECRYPT_MODE, key, gcmParameterSpec);

        // 7. Decrypt the message
        byte[] plainText = cipher.doFinal(cipherText);

        // 8. Convert the decrypted byte array to a String
        return new String(plainText, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            String message = "the solutions are here somewhere";
            System.out.println("Secret Message: " + message);

            // Equivalent to Fernet.generate_key()
            // Fernet uses a 256-bit key (128 for AES, 128 for HMAC-SHA256)
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey key = keyGen.generateKey();

            // The 'fernet' object in Python is conceptually similar to having the key
            // and using it with the Cipher object in Java.

            byte[] encMessage = encrypt(message, key);
            // In Python, printing bytes shows a b'...' literal.
            // In Java, it's standard to Base64-encode byte arrays for printing.
            System.out.println("Encrypted (Base64): " + Base64.getEncoder().encodeToString(encMessage));

            String decMessage = decrypt(encMessage, key);
            System.out.println("Decrypted Message: " + decMessage);

        } catch (Exception e) {
            System.err.println("An error occurred during the cryptographic operation.");
            e.printStackTrace();
        }
    }
}