import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AESHelper {

    /**
     * Encrypts a message using AES in CBC mode with manual PKCS7 padding.
     *
     * @param plainText The message to encrypt.
     * @param key       The AES key (must be 16, 24, or 32 bytes long).
     * @return The encrypted ciphertext.
     * @throws Exception If an error occurs during encryption.
     */
    public static byte[] encrypt(String plainText, byte[] key) throws Exception {
        // Convert message to UTF-8 bytes
        byte[] messageBytes = plainText.getBytes(StandardCharsets.UTF_8);

        // Pad message with PKCS7 padding
        int blockSize = 16;
        int paddingLength = blockSize - (messageBytes.length % blockSize);
        byte[] paddedMessage = new byte[messageBytes.length + paddingLength];
        System.arraycopy(messageBytes, 0, paddedMessage, 0, messageBytes.length);
        Arrays.fill(paddedMessage, messageBytes.length, paddedMessage.length, (byte) paddingLength);

        // Create AES key and IV (zero IV assumed)
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]);

        // Initialize cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // Encrypt and return
        return cipher.doFinal(paddedMessage);
    }

    /**
     * Decrypts a message using AES in CBC mode and removes PKCS7 padding.
     *
     * @param ciphertext The encrypted data.
     * @param key        The AES key (must be 16, 24, or 32 bytes long).
     * @return The decrypted message as a string.
     * @throws Exception If an error occurs during decryption or padding is invalid.
     */
    public static String decrypt(byte[] ciphertext, byte[] key) throws Exception {
        // Create AES key and IV (zero IV assumed)
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]);

        // Initialize cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // Decrypt
        byte[] paddedMessage = cipher.doFinal(ciphertext);

        // Unpad
        int paddingLength = paddedMessage[paddedMessage.length - 1] & 0xFF;

        // Validate padding length
        if (paddingLength < 1 || paddingLength > 16) {
            throw new IllegalArgumentException("Invalid padding length: " + paddingLength);
        }

        // Validate all padding bytes
        for (int i = 0; i < paddingLength; i++) {
            int index = paddedMessage.length - paddingLength + i;
            if ((paddedMessage[index] & 0xFF) != paddingLength) {
                throw new IllegalArgumentException("Invalid padding byte at index: " + index);
            }
        }

        // Extract the original message
        byte[] messageBytes = new byte[paddedMessage.length - paddingLength];
        System.arraycopy(paddedMessage, 0, messageBytes, 0, messageBytes.length);

        // Convert to string and return
        return new String(messageBytes, StandardCharsets.UTF_8);
    }
}