import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AESExample {

    public static byte[] encryptString(byte[] message, Cipher obj) throws Exception {
        return obj.doFinal(message);
    }

    public static byte[] decryptString(byte[] ciphertext, Cipher obj) throws Exception {
        return obj.doFinal(ciphertext);
    }

    // Border line since we are sending an obj that is derived from a key rather a plaintext key
    public static void main(String[] args) throws Exception {
        byte[] keyBytes = "This is a key123".getBytes(StandardCharsets.UTF_8);
        byte[] ivBytes = "This is an IV456".getBytes(StandardCharsets.UTF_8);

        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        Cipher obj1 = Cipher.getInstance("AES/CFB8/NoPadding");
        obj1.init(Cipher.ENCRYPT_MODE, key, iv);

        Cipher obj2 = Cipher.getInstance("AES/CFB8/NoPadding");
        obj2.init(Cipher.DECRYPT_MODE, key, iv);

        String message = "The answer is no";
        byte[] enc = encryptString(message.getBytes(StandardCharsets.UTF_8), obj1);
        byte[] dec = decryptString(enc, obj2);
    }
}