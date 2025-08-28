import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESCipher {

    private static final int BS = 32;
    private SecretKeySpec secretKey;

    public AESCipher(String key) throws NoSuchAlgorithmException {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        keyBytes = sha.digest(keyBytes);
        secretKey = new SecretKeySpec(keyBytes, "AES");
    }

    public String encrypt(String raw) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[cipher.getBlockSize()];
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParams);
        byte[] encrypted = cipher.doFinal(pad(raw).getBytes(StandardCharsets.UTF_8));
        byte[] ivAndEncrypted = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, ivAndEncrypted, 0, iv.length);
        System.arraycopy(encrypted, 0, ivAndEncrypted, iv.length, encrypted.length);
        return Base64.getEncoder().encodeToString(ivAndEncrypted);
    }

    public String decrypt(String enc) throws Exception {
        byte[] ivAndEncrypted = Base64.getDecoder().decode(enc);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] iv = new byte[cipher.getBlockSize()];
        System.arraycopy(ivAndEncrypted, 0, iv, 0, iv.length);
        IvParameterSpec ivParams = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParams);
        byte[] original = cipher.doFinal(ivAndEncrypted, iv.length, ivAndEncrypted.length - iv.length);
        return unpad(new String(original, StandardCharsets.UTF_8));
    }

    private String pad(String s) {
        int paddingLength = BS - (s.length() % BS);
        char paddingChar = (char) paddingLength;
        StringBuilder padded = new StringBuilder(s);
        for (int i = 0; i < paddingLength; i++) {
            padded.append(paddingChar);
        }
        return padded.toString();
    }

    private String unpad(String s) {
        int paddingLength = s.charAt(s.length() - 1);
        return s.substring(0, s.length() - paddingLength);
    }

    public static void main(String[] args) {
        try {
            String key = "mysecretpassword";
            AESCipher cipher = new AESCipher(key);
            String encrypted = cipher.encrypt("Secret Message A");
            String decrypted = cipher.decrypt(encrypted);

            System.out.println("Encrypted: " + encrypted);
            System.out.println("Decrypted: " + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}