import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESCfbSample {

    /* ---------- Equivalent to encrypt_string(...) ---------- */
    public static byte[] encryptString(String message, Cipher cipher) throws Exception {
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    /* ---------- Equivalent to decrypt_string(...) ---------- */
    public static String decryptString(byte[] cipherText, Cipher cipher) throws Exception {
        byte[] plainBytes = cipher.doFinal(cipherText);
        return new String(plainBytes, StandardCharsets.UTF_8);
    }

    /* ------------------------------------------------------- */
    public static void main(String[] args) throws Exception {

        /* Same literal key / IV used in the Python snippet */
        String key = "This is a key123";   // 16-bytes  (128-bit key)
        String iv  = "This is an IV456";   // 16-bytes  (128-bit IV)

        SecretKeySpec keySpec   = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivSpec  = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));

        /* obj1  → encryption cipher (same as Python’s obj1) */
        Cipher obj1 = Cipher.getInstance("AES/CFB/NoPadding");
        obj1.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        /* obj2  → decryption cipher (same as Python’s obj2) */
        Cipher obj2 = Cipher.getInstance("AES/CFB/NoPadding");
        obj2.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        /* Test run */
        String message = "The answer is no";
        byte[] enc = encryptString(message, obj1);          // encryption
        String dec = decryptString(enc, obj2);              // decryption

        /* Display results */
        System.out.println("Cipher-text (Base64): " +
                Base64.getEncoder().encodeToString(enc));
        System.out.println("Decrypted text: " + dec);
    }
}