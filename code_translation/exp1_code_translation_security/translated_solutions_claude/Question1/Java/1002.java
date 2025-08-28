import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Arrays;

public class AESCrypto {
    
    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        
        return cipher.doFinal(data);
    }
    
    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        
        return cipher.doFinal(data);
    }
    
    public static void test() throws Exception {
        byte[] data = "a secret message".getBytes();
        
        // Generate random key (32 bytes for AES-256)
        byte[] key = new byte[32];
        SecureRandom.getInstanceStrong().nextBytes(key);
        
        // Generate random IV (16 bytes for AES)
        byte[] iv = new byte[16];
        SecureRandom.getInstanceStrong().nextBytes(iv);
        
        byte[] encrypted = encrypt(data, key, iv);
        byte[] decrypted = decrypt(encrypted, key, iv);
        
        assert Arrays.equals(decrypted, data) : "Decrypted data doesn't match original";
        
        System.out.println("Test passed!");
    }
    
    public static void main(String[] args) {
        try {
            test();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}