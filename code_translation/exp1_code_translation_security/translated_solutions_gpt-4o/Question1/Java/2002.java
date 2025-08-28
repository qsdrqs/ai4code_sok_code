import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESCipher {

    // Encrypt method
    public static byte[] encrypt(byte[] key, String message) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    // Decrypt method
    public static String decrypt(byte[] key, byte[] cipherText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(cipherText);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // Test method
    public static void test() {
        try {
            byte[] key = "This_key_for_demo_purposes_only!".getBytes(StandardCharsets.UTF_8);
            byte[] cipherText = encrypt(key, "Text may be any length you wish, no padding is required");
            String decryptedText = decrypt(key, cipherText);
            System.out.println(decryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test();
    }
}