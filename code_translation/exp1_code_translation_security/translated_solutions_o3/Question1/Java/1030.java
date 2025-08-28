import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AESCipherExample {

    /* -------------------------------------------------
     *  Generates a random AES key, then Base64-encodes it
     *  ------------------------------------------------- */
    public static String generateSecretKeyForAESCipher() {
        final int AES_KEY_LENGTH = 16;          // use 24 or 32 in production
        byte[] rawKey = new byte[AES_KEY_LENGTH];

        new SecureRandom().nextBytes(rawKey);   // cryptographically-strong RNG

        // Store / transmit the key as Base64 text
        return Base64.getEncoder().encodeToString(rawKey);
    }

    /* ----------------------
     *  Function 1 – Encrypt
     *  ---------------------- */
    public static String encryptMessage(String privateMsg,
                                        String base64SecretKey,
                                        char paddingCharacter) throws Exception {

        byte[] secretKey = Base64.getDecoder().decode(base64SecretKey);

        // AES/ECB with *NO* padding – we pad manually, exactly like the Python code
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secretKey, "AES"));

        // ----- manual padding -----
        int blockSize    = 16;
        int paddingSize  = (blockSize - (privateMsg.length() % blockSize)) % blockSize;
        StringBuilder sb = new StringBuilder(privateMsg);
        for (int i = 0; i < paddingSize; i++) {
            sb.append(paddingCharacter);
        }
        byte[] paddedBytes = sb.toString().getBytes(StandardCharsets.UTF_8);
        // --------------------------

        byte[] encrypted = cipher.doFinal(paddedBytes);

        // Return Base64 text version of ciphertext
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /* ----------------------
     *  Function 2 – Decrypt
     *  ---------------------- */
    public static String decryptMessage(String base64EncryptedMsg,
                                        String base64SecretKey,
                                        char paddingCharacter) throws Exception {

        byte[] secretKey  = Base64.getDecoder().decode(base64SecretKey);
        byte[] cipherText = Base64.getDecoder().decode(base64EncryptedMsg);

        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretKey, "AES"));

        byte[] decryptedBytes = cipher.doFinal(cipherText);
        String decrypted      = new String(decryptedBytes, StandardCharsets.UTF_8);

        // ----- manual unpadding -----
        int end = decrypted.length();
        while (end > 0 && decrypted.charAt(end - 1) == paddingCharacter) {
            end--;
        }
        return decrypted.substring(0, end);
    }

    /* ---- Test / demo ---- */
    public static void main(String[] args) throws Exception {

        String privateMsg = "\n My test string\n";
        char   paddingChr = '{';

        String secretKey     = generateSecretKeyForAESCipher();
        String encryptedMsg  = encryptMessage(privateMsg, secretKey, paddingChr);
        String decryptedMsg  = decryptMessage(encryptedMsg, secretKey, paddingChr);

        System.out.println("   Secret Key: " + secretKey    + " - (" + secretKey.length()    + ")");
        System.out.println("Encrypted Msg: " + encryptedMsg + " - (" + encryptedMsg.length() + ")");
        System.out.println("Decrypted Msg: " + decryptedMsg + " - (" + decryptedMsg.length() + ")");
    }
}