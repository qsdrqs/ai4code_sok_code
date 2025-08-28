import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class EncryptionUtil {
    
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final int IV_LENGTH = 16;
    
    public static byte[] encrypt(String message, byte[] key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        
        // Generate random IV
        byte[] iv = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] encryptedMessage = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        
        // Combine IV and encrypted message
        byte[] encryptedWithIv = new byte[IV_LENGTH + encryptedMessage.length];
        System.arraycopy(iv, 0, encryptedWithIv, 0, IV_LENGTH);
        System.arraycopy(encryptedMessage, 0, encryptedWithIv, IV_LENGTH, encryptedMessage.length);
        
        return encryptedWithIv;
    }
    
    public static String decrypt(byte[] encryptedMessage, byte[] key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        
        // Extract IV from the beginning of the encrypted message
        byte[] iv = new byte[IV_LENGTH];
        System.arraycopy(encryptedMessage, 0, iv, 0, IV_LENGTH);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        // Extract the actual encrypted data
        byte[] encrypted = new byte[encryptedMessage.length - IV_LENGTH];
        System.arraycopy(encryptedMessage, IV_LENGTH, encrypted, 0, encrypted.length);
        
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decryptedMessage = cipher.doFinal(encrypted);
        
        return new String(decryptedMessage, StandardCharsets.UTF_8);
    }
    
    // Utility method to generate a key (equivalent to Fernet.generate_key())
    public static byte[] generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(256); // 256-bit key
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }
    
    // Example usage
    public static void main(String[] args) {
        try {
            byte[] key = generateKey();
            String message = "Hello, World!";
            
            byte[] encrypted = encrypt(message, key);
            String decrypted = decrypt(encrypted, key);
            
            System.out.println("Original: " + message);
            System.out.println("Encrypted: " + Base64.getEncoder().encodeToString(encrypted));
            System.out.println("Decrypted: " + decrypted);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}