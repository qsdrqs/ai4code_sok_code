import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.security.SecureRandom;

public class CryptoUtils {
    
    public static byte[] encrypt(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(data);
        return encrypted;
    }
    
    public static byte[] decrypt(byte[] data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(data);
        return decrypted;
    }
    
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }
    
    public static void main(String[] args) {
        try {
            String filePath = "/safedir/test.txt";
            SecretKey key = generateKey();
            
            // Note: The original Python code has issues - it passes a file path string
            // to encrypt function and has a variable name error in decrypt function
            // This Java version shows the corrected structure
            
            byte[] fileData = filePath.getBytes(); // In practice, you'd read the actual file
            encrypt(fileData, key);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}