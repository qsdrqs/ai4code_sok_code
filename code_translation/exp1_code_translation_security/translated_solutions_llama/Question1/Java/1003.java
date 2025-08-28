import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.nio.charset.StandardCharsets;

public class Main {

    /**
     * Encrypts the given data using AES in ECB mode.
     *
     * @param data The data to be encrypted.
     * @param key  The encryption key.
     * @return The encrypted data.
     * @throws Exception If an error occurs during encryption.
     */
    public static byte[] encryptBytesAes(byte[] data, byte[] key) throws Exception {
        Key secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * Decrypts the given data using AES in ECB mode.
     *
     * @param data The data to be decrypted.
     * @param key  The decryption key.
     * @return The decrypted data.
     * @throws Exception If an error occurs during decryption.
     */
    public static byte[] decryptBytesAes(byte[] data, byte[] key) throws Exception {
        Key secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        String data = "Hello, World!";
        byte[] key = "0123456789ABCDEF".getBytes(StandardCharsets.UTF_8);
        byte[] encryptedData = encryptBytesAes(data.getBytes(StandardCharsets.UTF_8), key);
        byte[] decryptedData = decryptBytesAes(encryptedData, key);

        System.out.println("Original Data: " + data);
        System.out.println("Encrypted Data: " + java.util.Arrays.toString(encryptedData));
        System.out.println("Decrypted Data: " + new String(decryptedData, StandardCharsets.UTF_8));
    }
}