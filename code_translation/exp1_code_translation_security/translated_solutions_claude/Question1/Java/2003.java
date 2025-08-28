import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class AESExample {
    
    /**
     * Encodes a byte array using an AES key.
     */
    public static byte[] encode(byte[] inp, byte[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(inp);
        } catch (Exception e) {
            throw new RuntimeException("Encryption failed", e);
        }
    }
    
    /**
     * Decodes a byte array using an AES key.
     */
    public static byte[] decode(byte[] inp, byte[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(inp);
        } catch (Exception e) {
            throw new RuntimeException("Decryption failed", e);
        }
    }
    
    public static void main(String[] args) {
        byte[] inp = "1111111111111111".getBytes();
        byte[] key = "1111111111111111".getBytes();
        
        byte[] encoded = encode(inp, key);
        byte[] decoded = decode(encoded, key);
        
        assert Arrays.equals(decoded, inp) : "Decryption failed - arrays don't match";
        
        System.out.println("Test passed: encode/decode cycle successful");
    }
}