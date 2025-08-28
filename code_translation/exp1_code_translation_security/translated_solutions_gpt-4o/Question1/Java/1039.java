import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;

public class EncryptionUtil {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static String encrypt(String message, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedMessage = cipher.doFinal(message.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedMessage);
    }

    public static String decrypt(String encryptedMessage, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "BC");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedMessage = cipher.doFinal(Base64.getDecoder().decode(encryptedMessage));
        return new String(decryptedMessage, "UTF-8");
    }

    public static void main(String[] args) {
        try {
            // Example usage
            String key = "your-base64-encoded-key"; // Replace with your actual key
            String message = "Hello, World!";
            
            String encryptedMessage = encrypt(message, key);
            System.out.println("Encrypted Message: " + encryptedMessage);
            
            String decryptedMessage = decrypt(encryptedMessage, key);
            System.out.println("Decrypted Message: " + decryptedMessage);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}