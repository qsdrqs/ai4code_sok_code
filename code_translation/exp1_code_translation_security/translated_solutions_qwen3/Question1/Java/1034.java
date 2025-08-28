import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class Main {

    public static String encodeStr(String plainText, String key) throws Exception {
        // Convert key to bytes using UTF-8
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        // Generate MD5 hash of the key
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] keyHash = md.digest(keyBytes);

        // Use the first 16 bytes of the MD5 hash as the AES key
        byte[] aesKey = new byte[16];
        System.arraycopy(keyHash, 0, aesKey, 0, 16);

        // Initialize AES ECB cipher for encryption
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        // Encrypt the plain text
        byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Base64 encode the encrypted bytes
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decodeStr(String encryptedStr, String key) throws Exception {
        // Convert key to bytes using UTF-8
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        // Generate MD5 hash of the key
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] keyHash = md.digest(keyBytes);

        // Use the first 16 bytes of the MD5 hash as the AES key
        byte[] aesKey = new byte[16];
        System.arraycopy(keyHash, 0, aesKey, 0, 16);

        // Initialize AES ECB cipher for decryption
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);

        // Base64 decode the encrypted string
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedStr);

        // Decrypt the data
        byte[] decrypted = cipher.doFinal(encryptedBytes);

        // Convert back to string using UTF-8
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            String str_encoded1 = encodeStr("I am okay", "wow");
            System.out.println(decodeStr(str_encoded1, "wow"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}