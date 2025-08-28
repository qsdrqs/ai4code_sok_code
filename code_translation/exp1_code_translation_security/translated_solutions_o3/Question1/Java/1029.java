import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

/**
 * Java equivalent of the Python AEScipher class
 * (manual PKCS#7 padding, AES-ECB mode).
 */
public final class AEScipher {

    private final int blockSize;          // Block size (bytes)
    private final SecretKeySpec keySpec;  // Re-usable key wrapper

    /**
     * @param keyBytes  AES key (16, 24 or 32 bytes long)
     * @param blkSize   Block size the caller wants to use (AES uses 16)
     */
    public AEScipher(byte[] keyBytes, int blkSize) {
        this.blockSize = blkSize;
        this.keySpec   = new SecretKeySpec(keyBytes, "AES");
    }

    /**
     * Encrypts the supplied plaintext (manual PKCS#7 padding).
     *
     * @param plainText plaintext to encrypt
     * @return ciphertext
     * @throws Exception if any crypto error occurs
     */
    public byte[] encrypt(byte[] plainText) throws Exception {

        /* ----------  PKCS#7 padding  ---------- */
        int padLen  = blockSize - (plainText.length % blockSize);
        int newSize = plainText.length + padLen;

        byte[] padded = Arrays.copyOf(plainText, newSize);
        Arrays.fill(padded, plainText.length, newSize, (byte) padLen);

        /* ----------  AES/ECB/NoPadding encryption  ---------- */
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        return cipher.doFinal(padded);
    }

    /**
     * Decrypts the supplied ciphertext and removes the PKCS#7 padding.
     *
     * @param cipherText ciphertext to decrypt
     * @return original plaintext
     * @throws Exception if any crypto error occurs
     */
    public byte[] decrypt(byte[] cipherText) throws Exception {

        /* ----------  AES/ECB/NoPadding decryption  ---------- */
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        byte[] padded = cipher.doFinal(cipherText);

        /* ----------  Strip PKCS#7 padding  ---------- */
        int padLen = padded[padded.length - 1] & 0xFF;  // unsigned
        int msgLen = padded.length - padLen;

        return Arrays.copyOf(padded, msgLen);
    }
}