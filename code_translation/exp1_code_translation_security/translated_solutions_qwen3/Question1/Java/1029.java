import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class AESCipher {

    private byte[] key;
    private int blkSz;

    public AESCipher(byte[] key, int blkSz) {
        this.key = key;
        this.blkSz = blkSz;
    }

    public byte[] encrypt(byte[] msg) {
        try {
            // Calculate padding length
            int padLen = blkSz - (msg.length % blkSz);
            byte[] padded = new byte[msg.length + padLen];

            // Copy original message
            System.arraycopy(msg, 0, padded, 0, msg.length);

            // Fill padding bytes with the value of padLen
            Arrays.fill(padded, msg.length, padded.length, (byte) padLen);

            // Initialize AES ECB cipher with no padding (manual padding applied)
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            // Perform encryption
            return cipher.doFinal(padded);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }

    public byte[] decrypt(byte[] ciphertext) {
        try {
            // Initialize AES ECB cipher with no padding
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            // Perform decryption
            byte[] decrypted = cipher.doFinal(ciphertext);

            // Extract padding length from the last byte (unsigned)
            int padLen = decrypted[decrypted.length - 1] & 0xFF;

            // Remove padding
            byte[] result = new byte[decrypted.length - padLen];
            System.arraycopy(decrypted, 0, result, 0, result.length);

            return result;
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
}