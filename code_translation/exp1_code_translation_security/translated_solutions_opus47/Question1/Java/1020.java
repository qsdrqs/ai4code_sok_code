import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Base64;

public class AESCipher {

    public static byte[] encrypt(byte[] key, int keySize, byte[] plaintext) throws Exception {
        // Generate random 16-byte IV
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // AES/CBC/PKCS5Padding in Java is equivalent to AES-CBC with PKCS7 padding
        // (PKCS5Padding in Java actually applies PKCS7 for 16-byte blocks)
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // Java's Cipher handles padding internally (equivalent to pad(plaintext) + encrypt)
        byte[] cipherText = cipher.doFinal(plaintext);

        // Concatenate IV + cipherText
        byte[] combined = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(cipherText, 0, combined, iv.length, cipherText.length);

        // Base64 encode and return
        return Base64.getEncoder().encode(combined);
    }

    // decrypt cipher text using symmetric key
    public static byte[] decrypt(byte[] key, int keySize, byte[] ciphertext) throws Exception {
        // Base64 decode
        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);

        // Extract IV from first 16 bytes
        byte[] iv = new byte[16];
        System.arraycopy(decodedBytes, 0, iv, 0, 16);

        // Extract the actual encrypted content
        byte[] encryptedBytes = new byte[decodedBytes.length - 16];
        System.arraycopy(decodedBytes, 16, encryptedBytes, 0, decodedBytes.length - 16);

        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // doFinal handles both decrypt and unpad
        return cipher.doFinal(encryptedBytes);
    }
}