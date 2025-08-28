import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class AesUtils {

    /**
     * Encrypts the given plaintext bytes with AES-128/192/256 in ECB mode (no padding).
     *
     * @param data plaintext; length must be a multiple of 16 bytes
     * @param key  AES key (16, 24, or 32 bytes long)
     * @return encrypted ciphertext
     * @throws Exception if the cipher cannot be initialized or run
     */
    public static byte[] encryptBytesAes(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");   // mirrors PyCryptodome default
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * Decrypts the given ciphertext bytes with AES-128/192/256 in ECB mode (no padding).
     *
     * @param data ciphertext; length must be a multiple of 16 bytes
     * @param key  AES key (16, 24, or 32 bytes long) – same key used for encryption
     * @return decrypted plaintext
     * @throws Exception if the cipher cannot be initialized or run
     */
    public static byte[] decryptBytesAes(byte[] data, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    private AesUtils() {
        /* utility class – no instances allowed */
    }
}