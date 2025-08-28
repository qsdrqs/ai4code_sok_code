import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Functions that one encrypts and the other decrypts a given string using a given symmetric key
 */
public class AESCipher {
    
    private static final int BLOCK_SIZE = 32;
    private static final int AES_BLOCK_SIZE = 16;
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/NoPadding";
    
    private byte[] key;
    
    public AESCipher(String key) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            this.key = sha256.digest(key.getBytes("UTF-8"));
        } catch (Exception e) {
            throw new RuntimeException("Error creating AES cipher", e);
        }
    }
    
    public String encrypt(String raw) {
        try {
            String padded = pad(raw);
            
            // Generate random IV
            SecureRandom random = new SecureRandom();
            byte[] iv = new byte[AES_BLOCK_SIZE];
            random.nextBytes(iv);
            
            // Create cipher
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec keySpec = new SecretKeySpec(this.key, ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            
            // Encrypt
            byte[] encrypted = cipher.doFinal(padded.getBytes("UTF-8"));
            
            // Combine IV and encrypted data
            byte[] result = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, result, 0, iv.length);
            System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);
            
            // Base64 encode
            return Base64.getEncoder().encodeToString(result);
            
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }
    
    public String decrypt(String enc) {
        try {
            // Base64 decode
            byte[] encryptedData = Base64.getDecoder().decode(enc);
            
            // Extract IV
            byte[] iv = new byte[AES_BLOCK_SIZE];
            System.arraycopy(encryptedData, 0, iv, 0, AES_BLOCK_SIZE);
            
            // Extract encrypted content
            byte[] encrypted = new byte[encryptedData.length - AES_BLOCK_SIZE];
            System.arraycopy(encryptedData, AES_BLOCK_SIZE, encrypted, 0, encrypted.length);
            
            // Create cipher
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            SecretKeySpec keySpec = new SecretKeySpec(this.key, ALGORITHM);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            
            // Decrypt
            byte[] decrypted = cipher.doFinal(encrypted);
            
            // Unpad and return
            return unpad(new String(decrypted, "UTF-8"));
            
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }
    
    private String pad(String s) {
        int padding = BLOCK_SIZE - (s.length() % BLOCK_SIZE);
        StringBuilder padded = new StringBuilder(s);
        for (int i = 0; i < padding; i++) {
            padded.append((char) padding);
        }
        return padded.toString();
    }
    
    private static String unpad(String s) {
        if (s.length() == 0) {
            return s;
        }
        int padding = (int) s.charAt(s.length() - 1);
        return s.substring(0, s.length() - padding);
    }
    
    // Example usage (commented out)
    /*
    public static void main(String[] args) {
        String key = "mysecretpassword";
        AESCipher cipher = new AESCipher(key);
        String encrypted = cipher.encrypt("Secret Message A");
        String decrypted = cipher.decrypt(encrypted);
        
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
    */
}