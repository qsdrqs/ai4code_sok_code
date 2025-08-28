import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

public class AESCipher {

    public static byte[] encode(byte[] inp, byte[] key) throws Exception {
        /**
         * Encodes a byte array using an AES key.
         */
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(inp);
    }

    public static byte[] decode(byte[] inp, byte[] key) throws Exception {
        /**
         * Decodes a byte array using an AES key.
         */
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(inp);
    }

    public static void main(String[] args) {
        try {
            byte[] inp = "1111111111111111".getBytes(); // Input string as bytes
            byte[] key = "1111111111111111".getBytes(); // Key as bytes

            // Encode and decode
            byte[] encoded = encode(inp, key);
            byte[] decoded = decode(encoded, key);

            // Assert that the decoded value matches the original input
            if (Arrays.equals(decoded, inp)) {
                System.out.println("Success: Decoded value matches the input.");
            } else {
                System.out.println("Failure: Decoded value does not match the input.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}