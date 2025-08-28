import javax.crypto.Cipher;
import javax.crypto.Key;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    /**
     * Encrypts the given data using AES in ECB mode with no padding.
     *
     * @param data The plaintext data to encrypt (must be a multiple of 16 bytes).
     * @param key  The AES key (must be 16, 24, or 32 bytes long).
     * @return The encrypted data.
     * @throws Exception If an error occurs during encryption.
     */
    public static byte[] encryptBytesAES(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        Key keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }

    /**
     * Decrypts the given data using AES in ECB mode with no padding.
     *
     * @param data The ciphertext data to decrypt (must be a multiple of 16 bytes).
     * @param key  The AES key (must be 16, 24, or 32 bytes long).
     * @return The decrypted data.
     * @throws Exception If an error occurs during decryption.
     */
    public static byte[] decryptBytesAES(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        Key keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }
}