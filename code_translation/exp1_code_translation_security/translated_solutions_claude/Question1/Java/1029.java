import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class AESCipher {
    
    private byte[] key;
    private int blkSz;
    
    public AESCipher(byte[] key, int blkSz) {
        this.key = key;
        this.blkSz = blkSz;
    }
    
    public byte[] encrypt(String msg) throws NoSuchAlgorithmException, 
            NoSuchPaddingException, InvalidKeyException, 
            IllegalBlockSizeException, BadPaddingException {
        
        // Convert string to bytes
        byte[] msgBytes = msg.getBytes(StandardCharsets.UTF_8);
        
        // Padding
        int padLen = blkSz - (msgBytes.length % blkSz);
        byte[] paddedMsg = new byte[msgBytes.length + padLen];
        
        // Copy original message
        System.arraycopy(msgBytes, 0, paddedMsg, 0, msgBytes.length);
        
        // Add padding bytes
        for (int i = msgBytes.length; i < paddedMsg.length; i++) {
            paddedMsg[i] = (byte) padLen;
        }
        
        // Encryption
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        
        return cipher.doFinal(paddedMsg);
    }
    
    public String decrypt(byte[] ciphertext) throws NoSuchAlgorithmException, 
            NoSuchPaddingException, InvalidKeyException, 
            IllegalBlockSizeException, BadPaddingException {
        
        // Decryption
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        
        // Remove padding
        int padLen = decryptedBytes[decryptedBytes.length - 1] & 0xFF;
        byte[] msg = new byte[decryptedBytes.length - padLen];
        System.arraycopy(decryptedBytes, 0, msg, 0, msg.length);
        
        return new String(msg, StandardCharsets.UTF_8);
    }
}