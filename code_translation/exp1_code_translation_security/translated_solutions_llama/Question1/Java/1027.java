import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class Main {

    // Function to encrypt a message
    public static byte[] encrypt(String message, byte[] key) throws Exception {
        // Create a new AES cipher instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        // Generate a random initialization vector (IV) for CBC mode
        byte[] iv = new byte[cipher.getBlockSize()];
        java.security.SecureRandom random = new java.security.SecureRandom();
        random.nextBytes(iv);
        
        // Create an IvParameterSpec object with the IV
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        // Create a SecretKeySpec object with the key
        Key secretKey = new SecretKeySpec(key, "AES");
        
        // Initialize the cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        
        // Convert the message to bytes and encrypt it
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] ciphertext = cipher.doFinal(messageBytes);
        
        // Combine the IV and ciphertext
        byte[] result = new byte[iv.length + ciphertext.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(ciphertext, 0, result, iv.length, ciphertext.length);
        
        // Return the combined IV and ciphertext
        return result;
    }

    // Function to decrypt a ciphertext
    public static String decrypt(byte[] ciphertext, byte[] key) throws Exception {
        // Create a new AES cipher instance
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        
        // Extract the IV from the ciphertext
        byte[] iv = new byte[cipher.getBlockSize()];
        System.arraycopy(ciphertext, 0, iv, 0, iv.length);
        
        // Extract the actual ciphertext
        byte[] actualCiphertext = new byte[ciphertext.length - iv.length];
        System.arraycopy(ciphertext, iv.length, actualCiphertext, 0, actualCiphertext.length);
        
        // Create an IvParameterSpec object with the IV
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        
        // Create a SecretKeySpec object with the key
        Key secretKey = new SecretKeySpec(key, "AES");
        
        // Initialize the cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        
        // Decrypt the ciphertext
        byte[] messageBytes = cipher.doFinal(actualCiphertext);
        
        // Convert the decrypted bytes to a string
        String message = new String(messageBytes, StandardCharsets.UTF_8);
        
        // Return the decrypted message
        return message;
    }

    public static void main(String[] args) throws Exception {
        // Example usage
        String keyString = "mysecretkey12345"; // Must be 16, 24, or 32 bytes long for AES
        byte[] key = keyString.getBytes(StandardCharsets.UTF_8);
        
        String message = "Hello, World!";
        byte[] ciphertext = encrypt(message, key);
        System.out.println("Ciphertext: " + Base64.getEncoder().encodeToString(ciphertext));
        
        String decryptedMessage = decrypt(ciphertext, key);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}