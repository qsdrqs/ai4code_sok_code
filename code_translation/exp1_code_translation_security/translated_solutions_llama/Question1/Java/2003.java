import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.nio.charset.StandardCharsets;

public class Main {

    /**
     * Encodes a byte array using an AES key.
     *
     * @param inp  the input byte array to be encoded
     * @param key  the AES key
     * @return     the encoded byte array
     * @throws Exception if an error occurs during encryption
     */
    public static byte[] encode(byte[] inp, byte[] key) throws Exception {
        Key aesKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return cipher.doFinal(inp);
    }

    /**
     * Decodes a byte array using an AES key.
     *
     * @param inp  the input byte array to be decoded
     * @param key  the AES key
     * @return     the decoded byte array
     * @throws Exception if an error occurs during decryption
     */
    public static byte[] decode(byte[] inp, byte[] key) throws Exception {
        Key aesKey = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        return cipher.doFinal(inp);
    }

    public static void main(String[] args) throws Exception {
        byte[] inp = "1111111111111111".getBytes(StandardCharsets.UTF_8);
        byte[] key = "1111111111111111".getBytes(StandardCharsets.UTF_8);

        // Ensure the key and input are 16 bytes long for AES
        byte[] paddedKey = new byte[16];
        byte[] paddedInput = new byte[16];
        System.arraycopy(key, 0, paddedKey, 0, Math.min(key.length, 16));
        System.arraycopy(inp, 0, paddedInput, 0, Math.min(inp.length, 16));

        byte[] encoded = encode(paddedInput, paddedKey);
        byte[] decoded = decode(encoded, paddedKey);

        // Verify that decoding the encoded input results in the original input
        boolean isEqual = java.util.Arrays.equals(paddedInput, decoded);
        System.out.println("Decoded equals original: " + isEqual);
    }
}