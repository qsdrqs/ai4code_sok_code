import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main {

    // Function to encode a string
    public static String encodeStr(String plainText, String key) throws Exception {
        // Generate a secret key from the provided key
        SecretKeySpec secretKeySpec = generateSecretKeySpec(key);

        // Create a Cipher instance
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

        // Encrypt the plain text
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        // Return the encoded string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Function to decode a string
    public static String decodeStr(String strEncoded, String key) throws Exception {
        // Generate a secret key from the provided key
        SecretKeySpec secretKeySpec = generateSecretKeySpec(key);

        // Create a Cipher instance
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

        // Decode the base64 encoded string
        byte[] decodedBytes = Base64.getDecoder().decode(strEncoded);

        // Decrypt the bytes
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        // Return the decoded string
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    // Helper function to generate a SecretKeySpec from a key
    private static SecretKeySpec generateSecretKeySpec(String key) throws NoSuchAlgorithmException {
        // Create a MessageDigest instance
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        // Digest the key
        byte[] digest = messageDigest.digest(key.getBytes(StandardCharsets.UTF_8));

        // Get the first 16 bytes of the digest
        byte[] keyBytes = new byte[16];
        System.arraycopy(digest, 0, keyBytes, 0, 16);

        // Return the SecretKeySpec
        return new SecretKeySpec(keyBytes, "AES");
    }

    public static void main(String[] args) throws Exception {
        String strEncoded1 = encodeStr("I am okay", "wow");
        System.out.println(decodeStr(strEncoded1, "wow"));
    }
}