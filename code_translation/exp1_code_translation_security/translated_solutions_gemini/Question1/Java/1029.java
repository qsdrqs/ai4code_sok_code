import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

/**
 * A Java translation of the provided Python AES cipher class.
 * This class performs AES encryption and decryption using ECB mode and manual PKCS#7 padding.
 */
public class AESCipher {

    private final byte[] key;
    private final int blockSize;
    private static final String ALGORITHM = "AES";
    // We use "NoPadding" because we are implementing the padding manually,
    // just like in the Python example.
    private static final String TRANSFORMATION = "AES/ECB/NoPadding";

    /**
     * Constructor for the AESCipher.
     *
     * @param key The encryption key (must be 16, 24, or 32 bytes for AES-128, AES-192, or AES-256).
     * @param blockSize The block size for AES, which is always 16 bytes (128 bits).
     */
    public AESCipher(byte[] key, int blockSize) {
        if (blockSize != 16) {
            throw new IllegalArgumentException("AES block size must be 16 bytes.");
        }
        if (key == null || (key.length != 16 && key.length != 24 && key.length != 32)) {
            throw new IllegalArgumentException("Invalid key size. Key must be 16, 24, or 32 bytes long.");
        }
        this.key = key;
        this.blockSize = blockSize;
    }

    /**
     * Encrypts a message with AES/ECB and PKCS#7 padding.
     *
     * @param message The plaintext message to encrypt.
     * @return The resulting ciphertext as a byte array.
     */
    public byte[] encrypt(String message) {
        try {
            // Convert message to bytes
            byte[] msgBytes = message.getBytes(StandardCharsets.UTF_8);

            // --- Padding ---
            int padLen = this.blockSize - (msgBytes.length % this.blockSize);
            // If the message length is a multiple of the block size, a full block of padding is added.
            if (padLen == 0) {
                padLen = this.blockSize;
            }

            byte[] paddedMsg = new byte[msgBytes.length + padLen];
            System.arraycopy(msgBytes, 0, paddedMsg, 0, msgBytes.length);
            // Fill the padding bytes with the value of the padding length
            for (int i = msgBytes.length; i < paddedMsg.length; i++) {
                paddedMsg[i] = (byte) padLen;
            }

            // --- Encryption ---
            SecretKeySpec secretKey = new SecretKeySpec(this.key, ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return cipher.doFinal(paddedMsg);

        } catch (Exception e) {
            // Wrap checked exceptions in a RuntimeException for simplicity
            throw new RuntimeException("Error during encryption", e);
        }
    }

    /**
     * Decrypts a ciphertext that was encrypted with AES/ECB and PKCS#7 padding.
     *
     * @param ciphertext The encrypted data as a byte array.
     * @return The original decrypted message as a byte array.
     *         You can convert this to a String using `new String(bytes, StandardCharsets.UTF_8)`.
     */
    public byte[] decrypt(byte[] ciphertext) {
        try {
            // --- Decryption ---
            SecretKeySpec secretKey = new SecretKeySpec(this.key, ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decryptedPaddedMsg = cipher.doFinal(ciphertext);

            // --- Remove Padding ---
            // The value of the last byte is the length of the padding
            int padLen = decryptedPaddedMsg[decryptedPaddedMsg.length - 1];

            // Basic validation for padding
            if (padLen <= 0 || padLen > this.blockSize) {
                throw new javax.crypto.BadPaddingException("Invalid padding length: " + padLen);
            }

            // Check if all padding bytes are correct
            for (int i = 1; i <= padLen; i++) {
                if (decryptedPaddedMsg[decryptedPaddedMsg.length - i] != padLen) {
                    throw new javax.crypto.BadPaddingException("Invalid padding byte found.");
                }
            }

            return Arrays.copyOfRange(decryptedPaddedMsg, 0, decryptedPaddedMsg.length - padLen);

        } catch (Exception e) {
            throw new RuntimeException("Error during decryption", e);
        }
    }

    /**
     * Main method to demonstrate the usage of the AESCipher class.
     */
    public static void main(String[] args) {
        // A 16-byte key for AES-128
        byte[] key = "MySuperSecretKey".getBytes(StandardCharsets.UTF_8);
        int blockSize = 16; // AES block size is always 16 bytes

        AESCipher aesCipher = new AESCipher(key, blockSize);

        String originalMessage = "This is a secret message.";
        System.out.println("Original Message: " + originalMessage);

        // Encrypt the message
        byte[] ciphertext = aesCipher.encrypt(originalMessage);
        // We use Base64 to get a printable representation of the ciphertext
        System.out.println("Encrypted (Base64): " + Base64.getEncoder().encodeToString(ciphertext));

        // Decrypt the message
        byte[] decryptedBytes = aesCipher.decrypt(ciphertext);
        String decryptedMessage = new String(decryptedBytes, StandardCharsets.UTF_8);
        System.out.println("Decrypted Message: " + decryptedMessage);

        // Verify the result
        System.out.println("Success: " + originalMessage.equals(decryptedMessage));
    }
}