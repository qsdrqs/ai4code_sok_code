import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class Main {

    /**
     * Encrypts a message using AES in CTR mode.
     *
     * @param k 256-bit UTF-8 encoded key.
     * @param m Message to be encrypted.
     * @return Encrypted message.
     * @throws Exception If an error occurs during encryption.
     */
    public static byte[] encrypt(byte[] k, String m) throws Exception {
        // Generate a random 16-byte IV for CTR mode
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);

        // Create a SecretKeySpec from the provided key
        SecretKeySpec keySpec = new SecretKeySpec(k, "AES");

        // Create an IvParameterSpec from the generated IV
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Create a Cipher instance in CTR mode
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        // Encrypt the message
        byte[] encryptedMessage = cipher.doFinal(m.getBytes(StandardCharsets.UTF_8));

        // Prepend the IV to the encrypted message
        byte[] result = new byte[iv.length + encryptedMessage.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(encryptedMessage, 0, result, iv.length, encryptedMessage.length);

        return result;
    }

    /**
     * Decrypts a message using AES in CTR mode.
     *
     * @param k 256-bit UTF-8 encoded key.
     * @param ct Encrypted message.
     * @return Decrypted message.
     * @throws Exception If an error occurs during decryption.
     */
    public static String decrypt(byte[] k, byte[] ct) throws Exception {
        // Extract the IV from the encrypted message
        byte[] iv = new byte[16];
        System.arraycopy(ct, 0, iv, 0, iv.length);

        // Extract the encrypted message
        byte[] encryptedMessage = new byte[ct.length - iv.length];
        System.arraycopy(ct, iv.length, encryptedMessage, 0, encryptedMessage.length);

        // Create a SecretKeySpec from the provided key
        SecretKeySpec keySpec = new SecretKeySpec(k, "AES");

        // Create an IvParameterSpec from the extracted IV
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Create a Cipher instance in CTR mode
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        // Decrypt the message
        byte[] decryptedMessage = cipher.doFinal(encryptedMessage);

        // Return the decrypted message as a string
        return new String(decryptedMessage, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        // Define a round-trip test
        byte[] k = "This_key_for_demo_purposes_only!".getBytes(StandardCharsets.UTF_8);
        String m = "Text may be any length you wish, no padding is required";
        byte[] ct = encrypt(k, m);
        System.out.println(decrypt(k, ct));
    }
}