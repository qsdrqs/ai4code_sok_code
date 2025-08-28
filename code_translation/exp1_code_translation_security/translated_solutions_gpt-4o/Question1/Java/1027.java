import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

public class AESUtil {

    private static final int AES_BLOCK_SIZE = 16; // AES block size is 16 bytes

    // Method to pad the message to match AES block size
    private static byte[] pad(byte[] data) {
        int paddingLength = AES_BLOCK_SIZE - (data.length % AES_BLOCK_SIZE);
        byte[] paddedData = Arrays.copyOf(data, data.length + paddingLength);
        Arrays.fill(paddedData, data.length, paddedData.length, (byte) paddingLength);
        return paddedData;
    }

    // Method to unpad the message after decryption
    private static byte[] unpad(byte[] data) {
        int paddingLength = data[data.length - 1];
        return Arrays.copyOf(data, data.length - paddingLength);
    }

    // Encrypt method
    public static byte[] encrypt(String message, byte[] key) throws Exception {
        // Convert message to bytes
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        // Pad the message
        byte[] paddedMessage = pad(messageBytes);

        // Generate a random IV (Initialization Vector)
        byte[] iv = new byte[AES_BLOCK_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Create cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        // Encrypt the message
        byte[] ciphertext = cipher.doFinal(paddedMessage);

        // Combine IV and ciphertext (IV is needed for decryption)
        byte[] result = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(ciphertext, 0, result, iv.length, ciphertext.length);

        return result;
    }

    // Decrypt method
    public static String decrypt(byte[] ciphertextWithIv, byte[] key) throws Exception {
        // Extract IV from the beginning of the ciphertext
        byte[] iv = Arrays.copyOfRange(ciphertextWithIv, 0, AES_BLOCK_SIZE);
        byte[] ciphertext = Arrays.copyOfRange(ciphertextWithIv, AES_BLOCK_SIZE, ciphertextWithIv.length);

        // Create cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        // Decrypt the message
        byte[] decryptedPaddedMessage = cipher.doFinal(ciphertext);

        // Unpad the message
        byte[] decryptedMessage = unpad(decryptedPaddedMessage);

        // Convert message to string
        return new String(decryptedMessage, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            // Example usage
            String message = "Hello, World!";
            byte[] key = new byte[16]; // 16 bytes key for AES-128
            SecureRandom random = new SecureRandom();
            random.nextBytes(key);

            // Encrypt
            byte[] encrypted = encrypt(message, key);
            System.out.println("Encrypted: " + Arrays.toString(encrypted));

            // Decrypt
            String decrypted = decrypt(encrypted, key);
            System.out.println("Decrypted: " + decrypted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}