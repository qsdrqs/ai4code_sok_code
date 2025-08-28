import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class AESCTRExample {
    
    // Where k is 256 bit UTF-8 encoded.
    public static byte[] encrypt(byte[] k, String m) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(k, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        
        // Generate a random IV (16 bytes for AES)
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(m.getBytes(StandardCharsets.UTF_8));
        
        // Prepend IV to encrypted data
        byte[] result = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);
        
        return result;
    }
    
    // Where k is UTF-8 encoded.
    public static String decrypt(byte[] k, byte[] ct) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(k, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        
        // Extract IV from the beginning of ciphertext
        byte[] iv = new byte[16];
        System.arraycopy(ct, 0, iv, 0, 16);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        // Extract actual encrypted data
        byte[] encryptedData = new byte[ct.length - 16];
        System.arraycopy(ct, 16, encryptedData, 0, encryptedData.length);
        
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decrypted = cipher.doFinal(encryptedData);
        
        return new String(decrypted, StandardCharsets.UTF_8);
    }
    
    public static void test() {
        roundTripTest();
    }
    
    public static void roundTripTest() {
        try {
            byte[] k = "This_key_for_demo_purposes_only!".getBytes(StandardCharsets.UTF_8);
            byte[] ct = encrypt(k, "Text may be any length you wish, no padding is required");
            System.out.println(decrypt(k, ct));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        test();
    }
}