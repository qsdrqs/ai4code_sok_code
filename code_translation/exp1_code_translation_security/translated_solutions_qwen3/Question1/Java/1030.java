import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AESExample {

    public static void main(String[] args) throws Exception {
        String privateMsg = "\n My test string\n";
        char paddingChar = '{';

        String secretKey = generateSecretKey();
        String encryptedMsg = encryptMessage(privateMsg, secretKey, paddingChar);
        String decryptedMsg = decryptMessage(encryptedMsg, secretKey, paddingChar);

        System.out.println("   Secret Key: " + secretKey + " - (" + secretKey.length() + ")");
        System.out.println("Encrypted Msg: " + encryptedMsg + " - (" + encryptedMsg.length() + ")");
        System.out.println("Decrypted Msg: " + decryptedMsg + " - (" + decryptedMsg.length() + ")");
    }

    /**
     * Generates a 16-byte random secret key and encodes it in Base64.
     */
    public static String generateSecretKey() throws Exception {
        byte[] key = new byte[16];
        new SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    /**
     * Encrypts a message using AES in ECB mode with manual padding.
     */
    public static String encryptMessage(String privateMsg, String encodedSecretKey, char paddingChar) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(encodedSecretKey);
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(decodedKey, "AES"));

        byte[] msgBytes = privateMsg.getBytes(StandardCharsets.UTF_8);
        int paddingLength = (16 - (msgBytes.length % 16)) % 16;
        byte[] paddedBytes = new byte[msgBytes.length + paddingLength];

        System.arraycopy(msgBytes, 0, paddedBytes, 0, msgBytes.length);
        Arrays.fill(paddedBytes, msgBytes.length, paddedBytes.length, (byte) paddingChar);

        byte[] encryptedBytes = cipher.doFinal(paddedBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts a message using AES in ECB mode and removes manual padding.
     */
    public static String decryptMessage(String encodedEncryptedMsg, String encodedSecretKey, char paddingChar) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(encodedSecretKey);
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decodedKey, "AES"));

        byte[] encryptedBytes = Base64.getDecoder().decode(encodedEncryptedMsg);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        String decryptedStr = new String(decryptedBytes, StandardCharsets.UTF_8);

        // Remove trailing padding characters
        int i = decryptedStr.length() - 1;
        while (i >= 0 && decryptedStr.charAt(i) == paddingChar) {
            i--;
        }

        return decryptedStr.substring(0, i + 1);
    }
}