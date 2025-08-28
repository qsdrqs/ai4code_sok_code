import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Arrays;

public final class AESCBC {

    private static final int IV_LENGTH = 16;          // 128-bit IV (AES block size)

    private AESCBC() { }                              // utility class – do not instantiate

    /**
     * Encrypts a byte array using AES-CBC + PKCS#5/#7 padding.
     *
     * @param key       raw key bytes (16, 24, or 32 bytes for AES-128/192/256)
     * @param keySize   kept only to stay consistent with the Python signature; not used here
     * @param plaintext data to encrypt
     * @return Base64 string containing  IV || CIPHERTEXT
     */
    public static String encrypt(byte[] key, int keySize, byte[] plaintext)
            throws GeneralSecurityException {

        // 1. Generate a random IV
        byte[] iv = new byte[IV_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        // 2. Build cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec   = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec  = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // 3. Encrypt and prepend IV
        byte[] cipherText = cipher.doFinal(plaintext);
        byte[] ivAndCipher = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, ivAndCipher, 0, iv.length);
        System.arraycopy(cipherText, 0, ivAndCipher, iv.length, cipherText.length);

        // 4. Return Base64
        return Base64.getEncoder().encodeToString(ivAndCipher);
    }

    /**
     * Decrypts data previously produced by {@link #encrypt}.
     *
     * @param key        raw key bytes (same key used to encrypt)
     * @param keySize    kept only to stay consistent with the Python signature; not used here
     * @param ciphertext Base64 string containing IV || CIPHERTEXT
     * @return plaintext bytes
     */
    public static byte[] decrypt(byte[] key, int keySize, String ciphertext)
            throws GeneralSecurityException {

        // 1. Decode Base64 and split IV / cipherText
        byte[] ivAndCipher = Base64.getDecoder().decode(ciphertext);
        byte[] iv          = Arrays.copyOfRange(ivAndCipher, 0, IV_LENGTH);
        byte[] cipherBytes = Arrays.copyOfRange(ivAndCipher, IV_LENGTH, ivAndCipher.length);

        // 2. Build cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec   = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec  = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // 3. Decrypt (padding removed automatically)
        return cipher.doFinal(cipherBytes);
    }
}