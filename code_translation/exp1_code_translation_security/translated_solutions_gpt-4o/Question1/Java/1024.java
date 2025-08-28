import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESCipherExample {

    public static String encryptString(String message, Cipher cipher) throws Exception {
        byte[] encrypted = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decryptString(String ciphertext, Cipher cipher) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(ciphertext);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        try {
            String key = "This is a key123";
            String initVector = "This is an IV456";

            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipherEncrypt = Cipher.getInstance("AES/CFB/NoPadding");
            cipherEncrypt.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            Cipher cipherDecrypt = Cipher.getInstance("AES/CFB/NoPadding");
            cipherDecrypt.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            String message = "The answer is no";
            String enc = encryptString(message, cipherEncrypt);
            String dec = decryptString(enc, cipherDecrypt);

            System.out.println("Encrypted: " + enc);
            System.out.println("Decrypted: " + dec);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}