import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AESExample {

    /**
     * Encrypts a given string using the provided Cipher instance.
     *
     * @param message The plaintext message to encrypt.
     * @param cipher  The initialized AES Cipher in ENCRYPT_MODE.
     * @return The encrypted byte array.
     * @throws Exception If an error occurs during encryption.
     */
    public static byte[] encryptString(String message, Cipher cipher) throws Exception {
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decrypts a given byte array using the provided Cipher instance.
     *
     * @param ciphertext The encrypted byte array to decrypt.
     * @param cipher     The initialized AES Cipher in DECRYPT_MODE.
     * @return The decrypted plaintext string.
     * @throws Exception If an error occurs during decryption.
     */
    public static byte[] decryptString(byte[] ciphertext, Cipher cipher) throws Exception {
        return cipher.doFinal(ciphertext);
    }

    public static void main(String[] args) throws Exception {
        // Original key and IV used in the Python code
        String key = "This is a key123";
        String iv = "This is an IV456";
        String message = "The answer is no";

        // Convert key and IV to byte arrays using UTF-8 encoding
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        byte[] ivBytes = iv.getBytes(StandardCharsets.UTF_8);

        // Create key and IV specifications
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // Create and initialize the encryption cipher (CFB8 mode)
        Cipher encryptCipher = Cipher.getInstance("AES/CFB8/NoPadding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // Create and initialize the decryption cipher (CFB8 mode)
        Cipher decryptCipher = Cipher.getInstance("AES/CFB8/NoPadding");
        decryptCipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // Perform encryption
        byte[] encrypted = encryptString(message, encryptCipher);

        // Perform decryption
        byte[] decrypted = decryptString(encrypted, decryptCipher);

        // Convert decrypted bytes back to string
        String decryptedMessage = new String(decrypted, StandardCharsets.UTF_8);

        // Output results
        System.out.println("Encrypted (hex): " + bytesToHex(encrypted));
        System.out.println("Decrypted: " + decryptedMessage);
    }

    /**
     * Converts a byte array to a hexadecimal string for display purposes.
     *
     * @param bytes The byte array to convert.
     * @return A hexadecimal string representation of the byte array.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}