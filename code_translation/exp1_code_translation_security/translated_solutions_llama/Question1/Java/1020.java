import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    // Function to pad the plaintext
    public static byte[] pad(byte[] plaintext) {
        int paddingLength = 16 - (plaintext.length % 16);
        byte[] paddedTxt = new byte[plaintext.length + paddingLength];
        System.arraycopy(plaintext, 0, paddedTxt, 0, plaintext.length);
        for (int i = plaintext.length; i < paddedTxt.length; i++) {
            paddedTxt[i] = (byte) paddingLength;
        }
        return paddedTxt;
    }

    // Function to unpad the decrypted text
    public static byte[] unpad(byte[] decryptedTxt) {
        int paddingLength = decryptedTxt[decryptedTxt.length - 1];
        byte[] unpaddedTxt = new byte[decryptedTxt.length - paddingLength];
        System.arraycopy(decryptedTxt, 0, unpaddedTxt, 0, unpaddedTxt.length);
        return unpaddedTxt;
    }

    // Function to encrypt plaintext using symmetric key
    public static String encrypt(byte[] key, String plaintext) throws Exception {
        // Generate a random 16-byte IV
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[16];
        secureRandom.nextBytes(iv);

        // Create an AES cipher object
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // Pad the plaintext
        byte[] paddedTxt = pad(plaintext.getBytes(StandardCharsets.UTF_8));

        // Encrypt the padded plaintext
        byte[] cipherTxt = cipher.doFinal(paddedTxt);

        // Combine IV and ciphertext, then base64 encode
        byte[] combined = new byte[iv.length + cipherTxt.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(cipherTxt, 0, combined, iv.length, cipherTxt.length);
        return Base64.getEncoder().encodeToString(combined);
    }

    // Function to decrypt ciphertext using symmetric key
    public static String decrypt(byte[] key, String ciphertext) throws Exception {
        // Base64 decode the ciphertext
        byte[] decodedCiphertext = Base64.getDecoder().decode(ciphertext);

        // Extract IV and ciphertext
        byte[] iv = new byte[16];
        byte[] cipherTxt = new byte[decodedCiphertext.length - 16];
        System.arraycopy(decodedCiphertext, 0, iv, 0, 16);
        System.arraycopy(decodedCiphertext, 16, cipherTxt, 0, cipherTxt.length);

        // Create an AES cipher object
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // Decrypt the ciphertext
        byte[] decryptedTxt = cipher.doFinal(cipherTxt);

        // Unpad the decrypted text
        return new String(unpad(decryptedTxt), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        byte[] key = "0123456789ABCDEF".getBytes(StandardCharsets.UTF_8); // 16-byte key
        String plaintext = "Hello, World!";
        String ciphertext = encrypt(key, plaintext);
        System.out.println("Ciphertext: " + ciphertext);
        String decryptedText = decrypt(key, ciphertext);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}