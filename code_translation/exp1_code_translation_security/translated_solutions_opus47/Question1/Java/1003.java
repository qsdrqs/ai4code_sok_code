import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {

    public static byte[] encryptBytesAes(byte[] data, byte[] key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }

    public static byte[] decryptBytesAes(byte[] data, byte[] key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        return cipher.doFinal(data);
    }
}