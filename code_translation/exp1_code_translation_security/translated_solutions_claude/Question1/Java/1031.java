import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class SymmetricEncryption {
    
    private SecretKey key;
    private Cipher cipher;
    
    public SymmetricEncryption(SecretKey key) throws Exception {
        this.key = key;
        this.cipher = Cipher.getInstance("AES");
    }
    
    /**
     * Generate a new AES key
     */
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }
    
    /**
     * encrypt using a symmetric key
     */
    public byte[] encrypt(String message) throws Exception {
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * decrypt using a symmetric key
     */
    public String decrypt(byte[] encMessage) throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(encMessage);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
    
    public static void main(String[] args) {
        try {
            String message = "the solutions are here somewhere";
            System.out.println("Secret Message: " + message);
            
            SecretKey key = generateKey();
            SymmetricEncryption encryption = new SymmetricEncryption(key);
            
            byte[] encMessage = encryption.encrypt(message);
            System.out.println(Base64.getEncoder().encodeToString(encMessage));
            
            String decMessage = encryption.decrypt(encMessage);
            System.out.println("decrypted Message: " + decMessage);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}