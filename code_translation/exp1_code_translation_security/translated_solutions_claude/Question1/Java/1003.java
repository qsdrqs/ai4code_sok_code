import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESUtil {
    
    public static byte[] encryptBytesAes(byte[] data, byte[] key) 
            throws NoSuchAlgorithmException, NoSuchPaddingException, 
                   InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }
    
    public static byte[] decryptBytesAes(byte[] data, byte[] key) 
            throws NoSuchAlgorithmException, NoSuchPaddingException, 
                   InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }
}