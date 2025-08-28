import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class FernetEncryption {
    
    public static byte[] encrypt(byte[] input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(input);
    }
    
    public static byte[] decrypt(byte[] input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(input);
    }
    
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }
    
    public static void main(String[] args) {
        try {
            SecretKey key = generateKey();
            byte[] input = "super secret".getBytes();
            byte[] encryptedInput = encrypt(input, key);
            byte[] decryptedInput = decrypt(encryptedInput, key);
            
            System.out.println(new String(input));
            System.out.println(new String(decryptedInput));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}