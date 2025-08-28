import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class AesCbcExample {

    /**
     * Encrypt the supplied plaintext.
     *
     * @param data Plaintext bytes (MUST already be a multiple of 16 – no padding is applied,
     *             mirroring the Python sample).
     * @param key  32-byte AES key (AES-256).
     * @param iv   16-byte IV.
     * @return Cipher-text bytes.
     */
    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(data);
    }

    /**
     * Decrypt the supplied cipher-text.
     *
     * @param data Cipher-text bytes.
     * @param key  32-byte AES key (AES-256).
     * @param iv   16-byte IV.
     * @return Decrypted plaintext bytes.
     */
    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(data);
    }

    /**
     * Simple self-test that mirrors the Python `test()` function.
     */
    public static void test() throws Exception {
        byte[] plaintext = "a secret message".getBytes(); // 15 bytes (⚠ not a multiple of 16!)
        // To keep the behaviour identical to the Python code, no padding is added,
        // so we need to manually make the plaintext a multiple of 16.
        int blockSize = 16;
        int paddedLen = ((plaintext.length + blockSize - 1) / blockSize) * blockSize;
        byte[] paddedPlaintext = Arrays.copyOf(plaintext, paddedLen); // zero-padding

        SecureRandom rnd = new SecureRandom();
        byte[] key = new byte[32];  // 256-bit key
        byte[] iv  = new byte[16];  // 128-bit IV
        rnd.nextBytes(key);
        rnd.nextBytes(iv);

        byte[] encrypted = encrypt(paddedPlaintext, key, iv);
        byte[] decrypted = decrypt(encrypted, key, iv);

        if (!Arrays.equals(decrypted, paddedPlaintext)) {
            throw new IllegalStateException("Decryption failed!");
        } else {
            System.out.println("Test passed.");
        }
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}