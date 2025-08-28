import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * A Java class that provides symmetric encryption and decryption functionalities
 * equivalent to the provided Python AESCipher class.
 * It uses AES/CBC/NoPadding, with a key derived using SHA-256.
 * The IV is prepended to the ciphertext, and the data is padded to a 32-byte block size.
 */
public class AESCipher {

    // In the Python code, bs=32 is used for padding, which is non-standard for AES.
    // We replicate this behavior for compatibility.
    private static final int PADDING_BLOCK_SIZE = 32;

    // AES standard block size is 16 bytes (128 bits), used for the IV.
    private static final int AES_BLOCK_SIZE = 16;

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/NoPadding";
    private static final String HASH_ALGORITHM = "SHA-256";

    private final SecretKeySpec secretKey;

    /**
     * Constructs an AESCipher instance.
     * The key is hashed using SHA-256 to produce a 32-byte (256-bit) key suitable for AES-256.
     *
     * @param key The secret key string.
     */
    public AESCipher(String key) {
        try {
            MessageDigest sha = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
            byte[] hashedKey = sha.digest(keyBytes);
            this.secretKey = new SecretKeySpec(hashedKey, ALGORITHM);
        } catch (Exception e) {
            // In a real application, more robust error handling would be needed.
            throw new RuntimeException("Error initializing AESCipher", e);
        }
    }

    /**
     * Encrypts a raw string.
     *
     * @param raw The string to encrypt.
     * @return A Base64 encoded string containing the IV and the encrypted data.
     */
    public String encrypt(String raw) throws Exception {
        byte[] rawBytes = raw.getBytes(StandardCharsets.UTF_8);
        byte[] paddedBytes = pad(rawBytes);

        // Generate a random IV (Initialization Vector)
        byte[] iv = new byte[AES_BLOCK_SIZE];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, this.secretKey, ivSpec);

        byte[] encryptedBytes = cipher.doFinal(paddedBytes);

        // Prepend the IV to the encrypted data
        byte[] combined = new byte[iv.length + encryptedBytes.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encryptedBytes, 0, combined, iv.length, encryptedBytes.length);

        // Base64 encode the combined IV and encrypted data
        return Base64.getEncoder().encodeToString(combined);
    }

    /**
     * Decrypts a Base64 encoded string.
     *
     * @param enc The Base64 encoded string to decrypt.
     * @return The original, decrypted string.
     */
    public String decrypt(String enc) throws Exception {
        // Base64 decode the input string
        byte[] combined = Base64.getDecoder().decode(enc);

        // Extract the IV from the beginning of the combined data
        byte[] iv = Arrays.copyOfRange(combined, 0, AES_BLOCK_SIZE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extract the actual encrypted data
        byte[] encryptedBytes = Arrays.copyOfRange(combined, AES_BLOCK_SIZE, combined.length);

        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(Cipher.DECRYPT_MODE, this.secretKey, ivSpec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Unpad the decrypted data
        byte[] unpaddedBytes = unpad(decryptedBytes);

        // Convert the unpadded bytes back to a string
        return new String(unpaddedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Pads the input byte array to be a multiple of PADDING_BLOCK_SIZE.
     * This is a Java implementation of the Python _pad method.
     */
    private byte[] pad(byte[] s) {
        int paddingSize = PADDING_BLOCK_SIZE - (s.length % PADDING_BLOCK_SIZE);
        // The padding value is the padding size itself
        byte paddingValue = (byte) paddingSize;

        byte[] padded = new byte[s.length + paddingSize];
        System.arraycopy(s, 0, padded, 0, s.length);
        Arrays.fill(padded, s.length, padded.length, paddingValue);

        return padded;
    }

    /**
     * Removes the padding from a decrypted byte array.
     * This is a Java implementation of the Python _unpad method.
     */
    private static byte[] unpad(byte[] s) {
        if (s == null || s.length == 0) {
            return new byte[0];
        }
        // The last byte contains the padding size.
        // We use & 0xFF to treat the byte as an unsigned value (0-255).
        int paddingSize = s[s.length - 1] & 0xFF;

        // Check if padding is valid
        if (paddingSize <= 0 || paddingSize > s.length) {
            // Or throw an exception for bad padding
            return s;
        }

        return Arrays.copyOfRange(s, 0, s.length - paddingSize);
    }

    /**
     * Main method to demonstrate the AESCipher class.
     */
    public static void main(String[] args) {
        try {
            String key = "mysecretpassword";
            AESCipher cipher = new AESCipher(key);

            String originalMessage = "Secret Message A";
            System.out.println("Original:  " + originalMessage);

            String encrypted = cipher.encrypt(originalMessage);
            System.out.println("Encrypted: " + encrypted);

            String decrypted = cipher.decrypt(encrypted);
            System.out.println("Decrypted: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}