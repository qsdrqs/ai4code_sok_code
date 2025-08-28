import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AesEcbExample {

    /**
     * Encodes (encrypts) a byte array using an AES key (ECB / NoPadding).
     */
    public static byte[] encode(byte[] input, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(input);
    }

    /**
     * Decodes (decrypts) a byte array using an AES key (ECB / NoPadding).
     */
    public static byte[] decode(byte[] input, byte[] key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(input);
    }

    public static void main(String[] args) throws Exception {
        byte[] inp = "1111111111111111".getBytes(StandardCharsets.UTF_8); // 16 bytes
        byte[] key = "1111111111111111".getBytes(StandardCharsets.UTF_8); // 16 bytes (128-bit AES key)

        byte[] encrypted = encode(inp, key);
        byte[] decrypted = decode(encrypted, key);

        // Mimic Python's `assert decode(encode(inp, key), key) == inp`
        if (!Arrays.equals(decrypted, inp)) {
            throw new AssertionError("Decryption failed: results don't match the original input.");
        }

        System.out.println("Success! Encryption and decryption are symmetric.");
    }
}