import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AESHelper {

    // AES block size in bytes
    private static final int BLOCK_SIZE = 16;

    /**
     * Encrypts the given plaintext using AES in CBC mode with manual PKCS#7 padding.
     *
     * @param key         The AES key (must be 16, 24, or 32 bytes for AES-128, AES-192, AES-256)
     * @param keySize     Ignored in this implementation (assumes key is valid)
     * @param plaintext   The plaintext to encrypt
     * @return            Base64-encoded string of IV + encrypted data
     * @throws Exception  If encryption fails
     */
    public static String encrypt(byte[] key, int keySize, String plaintext) throws Exception {
        byte[] iv = new byte[BLOCK_SIZE];
        new SecureRandom().nextBytes(iv);

        byte[] plainBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] padded = pad(plainBytes);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(padded);

        byte[] combined = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    /**
     * Decrypts the given Base64-encoded ciphertext using AES in CBC mode with manual PKCS#7 unpadding.
     *
     * @param key         The AES key (must be 16, 24, or 32 bytes)
     * @param keySize     Ignored in this implementation
     * @param ciphertext  Base64-encoded string of IV + encrypted data
     * @return            The decrypted plaintext
     * @throws Exception  If decryption fails
     */
    public static String decrypt(byte[] key, int keySize, String ciphertext) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(ciphertext);

        byte[] iv = Arrays.copyOfRange(decoded, 0, BLOCK_SIZE);
        byte[] cipherText = Arrays.copyOfRange(decoded, BLOCK_SIZE, decoded.length);

        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decrypted = cipher.doFinal(cipherText);

        byte[] unpaded = unpad(decrypted);
        return new String(unpaded, StandardCharsets.UTF_8);
    }

    /**
     * Adds PKCS#7 padding to the input data to make it a multiple of the block size.
     *
     * @param data The data to pad
     * @return     Padded data
     */
    private static byte[] pad(byte[] data) {
        int paddingLen = BLOCK_SIZE - (data.length % BLOCK_SIZE);
        byte[] padding = new byte[paddingLen];
        Arrays.fill(padding, (byte) paddingLen);
        byte[] padded = new byte[data.length + paddingLen];
        System.arraycopy(data, 0, padded, 0, data.length);
        System.arraycopy(padding, 0, padded, data.length, paddingLen);
        return padded;
    }

    /**
     * Removes PKCS#7 padding from the input data.
     *
     * @param data The data to unpad
     * @return     Unpadded data
     */
    private static byte[] unpad(byte[] data) {
        int paddingLen = data[data.length - 1] & 0xFF; // Convert to unsigned
        return Arrays.copyOfRange(data, 0, data.length - paddingLen);
    }
}