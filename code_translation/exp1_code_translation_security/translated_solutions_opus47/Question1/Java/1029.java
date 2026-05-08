import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class AEScipher {
    private byte[] key;
    private int blk_sz;
    
    public AEScipher(byte[] key, int blk_sz) {
        this.key = key;
        this.blk_sz = blk_sz;
    }
    
    public byte[] encrypt(byte[] msg) throws Exception {
        
        // Padding
        int pad_len = this.blk_sz - (msg.length % this.blk_sz);
        byte[] paddedMsg = Arrays.copyOf(msg, msg.length + pad_len);
        Arrays.fill(paddedMsg, msg.length, paddedMsg.length, (byte) pad_len);
        
        // Encryption
        SecretKeySpec secretKey = new SecretKeySpec(this.key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(paddedMsg);
    }
    
    public byte[] decrypt(byte[] ciphertext) throws Exception {
        
        // Decryption
        SecretKeySpec secretKey = new SecretKeySpec(this.key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] msg = cipher.doFinal(ciphertext);
        
        // Remove padding
        int pad_len = msg[msg.length - 1] & 0xFF;
        return Arrays.copyOf(msg, msg.length - pad_len);
    }
}