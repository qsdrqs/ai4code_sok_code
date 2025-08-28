import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class AEScipher {

    private final byte[] key;
    private final int blk_sz;

    public AEScipher(byte[] key, int blk_sz) {
        this.key = key;
        this.blk_sz = blk_sz;
    }

    public byte[] encrypt(String msg) throws Exception {
        // Padding
        int pad_len = blk_sz - (msg.length() % blk_sz);
        char padChar = (char) pad_len;
        StringBuilder paddedMsg = new StringBuilder(msg);
        for (int i = 0; i < pad_len; i++) {
            paddedMsg.append(padChar);
        }

        // Encryption
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(paddedMsg.toString().getBytes());
    }

    public String decrypt(byte[] ciphertext) throws Exception {
        // Decryption
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);

        // Remove padding
        int pad_len = decryptedBytes[decryptedBytes.length - 1];
        return new String(Arrays.copyOfRange(decryptedBytes, 0, decryptedBytes.length - pad_len));
    }
}