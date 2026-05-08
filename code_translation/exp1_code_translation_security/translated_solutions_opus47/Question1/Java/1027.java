import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class AESCipher {

    private static final int AES_BLOCK_SIZE = 16;

    public static byte[] encrypt(String message, byte[] key) throws Exception {
        // convert message to bytes
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        // create cipher (PKCS5Padding handles the padding automatically)
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        // generate random IV (as PyCryptodome does when IV is not specified)
        byte[] iv = new byte[AES_BLOCK_SIZE];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // encrypt message (padding applied automatically)
        byte[] ciphertext = cipher.doFinal(messageBytes);

        // return ciphertext
        return ciphertext;
    }

    public static String decrypt(byte[] ciphertext, byte[] key) throws Exception {
        // create cipher
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        // generate random IV (mirroring the Python behavior)
        byte[] iv = new byte[AES_BLOCK_SIZE];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // decrypt message (unpadding applied automatically)
        byte[] messageBytes = cipher.doFinal(ciphertext);

        // convert message to string
        String message = new String(messageBytes, StandardCharsets.UTF_8);

        // return message
        return message;
    }
}