import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class CryptoCode {
    
    public static String encodeStr(String plainText, String key) {
        try {
            // Create a key from the password using SHA-256
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = sha.digest(key.getBytes("UTF-8"));
            
            // Use only first 16 bytes for AES-128
            byte[] aesKey = new byte[16];
            System.arraycopy(keyBytes, 0, aesKey, 0, 16);
            
            SecretKeySpec secretKey = new SecretKeySpec(aesKey, "AES");
            
            // Create cipher instance
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            // Encrypt the text
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
            
            // Encode to Base64 string
            return Base64.getEncoder().encodeToString(encryptedBytes);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String decodeStr(String strEncoded, String key) {
        try {
            // Create a key from the password using SHA-256
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            byte[] keyBytes = sha.digest(key.getBytes("UTF-8"));
            
            // Use only first 16 bytes for AES-128
            byte[] aesKey = new byte[16];
            System.arraycopy(keyBytes, 0, aesKey, 0, 16);
            
            SecretKeySpec secretKey = new SecretKeySpec(aesKey, "AES");
            
            // Create cipher instance
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            // Decode from Base64
            byte[] encryptedBytes = Base64.getDecoder().decode(strEncoded);
            
            // Decrypt the bytes
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            
            return new String(decryptedBytes, "UTF-8");
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static void main(String[] args) {
        String strEncoded1 = encodeStr("I am okay", "wow");
        System.out.println(decodeStr(strEncoded1, "wow"));
    }
}