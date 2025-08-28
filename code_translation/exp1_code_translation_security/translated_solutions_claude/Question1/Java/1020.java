import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

public class AESCrypto {
    
    public static String encrypt(byte[] key, int keySize, String plaintext) throws Exception {
        // Generate random IV (16 bytes for AES)
        byte[] iv = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(iv);
        
        // Create cipher instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        // Initialize cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        
        // Encrypt the plaintext (padding is handled automatically by PKCS5Padding)
        byte[] paddedTxt = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] cipherTxt = cipher.doFinal(paddedTxt);
        
        // Combine IV and cipher text
        byte[] combined = new byte[iv.length + cipherTxt.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(cipherTxt, 0, combined, iv.length, cipherTxt.length);
        
        // Base64 encode the result
        return Base64.getEncoder().encodeToString(combined);
    }
    
    // Decrypt cipher text using symmetric key
    public static String decrypt(byte[] key, int keySize, String ciphertext) throws Exception {
        // Base64 decode the ciphertext
        byte[] decodedCiphertext = Base64.getDecoder().decode(ciphertext);
        
        // Extract IV (first 16 bytes)
        byte[] iv = new byte[16];
        System.arraycopy(decodedCiphertext, 0, iv, 0, 16);
        
        // Extract actual cipher text (remaining bytes)
        byte[] actualCiphertext = new byte[decodedCiphertext.length - 16];
        System.arraycopy(decodedCiphertext, 16, actualCiphertext, 0, actualCiphertext.length);
        
        // Create cipher instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        // Initialize cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        
        // Decrypt the ciphertext (unpadding is handled automatically by PKCS5Padding)
        byte[] plaintext = cipher.doFinal(actualCiphertext);
        
        return new String(plaintext, StandardCharsets.UTF_8);
    }
}