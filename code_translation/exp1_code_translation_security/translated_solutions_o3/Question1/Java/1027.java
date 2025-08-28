import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AESCipher {

    // AES parameters
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int    BLOCK_SIZE      = 16;     // 128-bit blocks

    /**
     * Encrypt a UTF-8 string with AES-CBC.
     *
     * @param message Plain-text message
     * @param key     Raw AES key (16/24/32 bytes for 128/192/256-bit keys)
     * @return        Cipher-text bytes
     *
     * @throws Exception if anything goes wrong
     */
    public static byte[] encrypt(String message, byte[] key) throws Exception {

        // Convert the message to bytes (UTF-8)
        byte[] data = message.getBytes(StandardCharsets.UTF_8);

        // ------------------------------------------------------------------
        // Create and initialize the cipher
        // (Padding is handled automatically by "PKCS5Padding")
        // ------------------------------------------------------------------
        Cipher          cipher   = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec   keySpec  = new SecretKeySpec(key, "AES");

        // The original Python snippet doesn’t keep or provide an IV, so we
        // use a constant zero-IV here to stay behaviour-compatible.
        // (In production code you should use a random IV and store/transmit it
        // alongside the cipher-text!)
        IvParameterSpec ivSpec   = new IvParameterSpec(new byte[BLOCK_SIZE]);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // Encrypt and return the result
        return cipher.doFinal(data);
    }

    /**
     * Decrypt cipher-text produced by {@link #encrypt}.
     *
     * @param ciphertext Encrypted bytes
     * @param key        Raw AES key (same key that was used to encrypt)
     * @return           Decrypted plain-text string
     *
     * @throws Exception if decryption fails
     */
    public static String decrypt(byte[] ciphertext, byte[] key) throws Exception {

        // ------------------------------------------------------------------
        // Create and initialize the cipher (same parameters as for encrypt)
        // ------------------------------------------------------------------
        Cipher          cipher   = Cipher.getInstance(TRANSFORMATION);
        SecretKeySpec   keySpec  = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec   = new IvParameterSpec(new byte[BLOCK_SIZE]);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // Decrypt, remove padding automatically, convert to UTF-8 string
        byte[] plaintext = cipher.doFinal(ciphertext);
        return new String(plaintext, StandardCharsets.UTF_8);
    }
}