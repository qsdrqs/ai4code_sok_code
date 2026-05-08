import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class AESExample {
    
    /**
     * Encodes a byte array using an AES key.
     */
    public static byte[] encode(byte[] inp, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(inp);
    }
    
    /**
     * Decodes a byte array using an AES key.
     */
    public static byte[] decode(byte[] inp, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(inp);
    }
    
    public static void main(String[] args) throws Exception {
        byte[] inp = "1111111111111111".getBytes();
        byte[] key = "1111111111111111".getBytes();
        assert Arrays.equals(decode(encode(inp, key), key), inp);
    }
}