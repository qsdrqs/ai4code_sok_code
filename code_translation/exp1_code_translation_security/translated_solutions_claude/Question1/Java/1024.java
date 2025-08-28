import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;

public class AESEncryption {
    
    public static byte[] encryptString(String message, Cipher cipher) {
        try {
            return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }
    
    public static String decryptString(byte[] ciphertext, Cipher cipher) {
        try {
            byte[] decryptedBytes = cipher.doFinal(ciphertext);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
    
    private static Cipher createAESCipher(String key, String iv, int mode) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            
            Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
            cipher.init(mode, keySpec, ivSpec);
            
            return cipher;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create cipher", e);
        }
    }
    
    public static void main(String[] args) {
        String key = "This is a key123";
        String iv = "This is an IV456";
        
        Cipher encryptCipher = createAESCipher(key, iv, Cipher.ENCRYPT_MODE);
        Cipher decryptCipher = createAESCipher(key, iv, Cipher.DECRYPT_MODE);
        
        String message = "The answer is no";
        byte[] enc = encryptString(message, encryptCipher);
        String dec = decryptString(enc, decryptCipher);
        
        System.out.println("Original message: " + message);
        System.out.println("Decrypted message: " + dec);
    }
}