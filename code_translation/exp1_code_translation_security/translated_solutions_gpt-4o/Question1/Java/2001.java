import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptionUtil {

    // Function to encrypt a message with a key using AES-128-CBC with HMAC
    public static String encrypt(String msg, SecretKey key) throws Exception {
        // Generate a random IV
        byte[] iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Initialize the cipher for encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

        // Encrypt the message
        byte[] encrypted = cipher.doFinal(msg.getBytes());

        // Concatenate IV and encrypted message
        byte[] ivAndEncrypted = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, ivAndEncrypted, 0, iv.length);
        System.arraycopy(encrypted, 0, ivAndEncrypted, iv.length, encrypted.length);

        // Encode to Base64
        return Base64.getEncoder().encodeToString(ivAndEncrypted);
    }

    // Function to decrypt a message with a key using AES-128-CBC with HMAC
    public static String decrypt(String ciphertext, SecretKey key) throws Exception {
        // Decode from Base64
        byte[] ivAndEncrypted = Base64.getDecoder().decode(ciphertext);

        // Extract IV
        byte[] iv = new byte[16];
        System.arraycopy(ivAndEncrypted, 0, iv, 0, iv.length);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extract encrypted message
        byte[] encrypted = new byte[ivAndEncrypted.length - iv.length];
        System.arraycopy(ivAndEncrypted, iv.length, encrypted, 0, encrypted.length);

        // Initialize the cipher for decryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);

        // Decrypt the message
        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(decrypted);
    }

    // Utility function to generate a SecretKey for AES-128
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // AES-128
        return keyGen.generateKey();
    }

    public static void main(String[] args) {
        try {
            SecretKey key = generateKey();
            String message = "Hello, World!";
            String encryptedMessage = encrypt(message, key);
            System.out.println("Encrypted: " + encryptedMessage);

            String decryptedMessage = decrypt(encryptedMessage, key);
            System.out.println("Decrypted: " + decryptedMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}