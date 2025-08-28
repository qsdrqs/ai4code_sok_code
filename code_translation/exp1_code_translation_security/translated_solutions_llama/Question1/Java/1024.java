import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Main {

    // Function to encrypt a string
    public static byte[] encryptString(String message, Cipher cipher) throws Exception {
        return cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
    }

    // Function to decrypt a string
    public static String decryptString(byte[] ciphertext, Cipher cipher) throws Exception {
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        // Create a secret key and IV
        String key = "This is a key123";
        String iv = "This is an IV456";

        // Create a SecretKey object
        SecretKey secretKey = createSecretKey(key);

        // Create an IvParameterSpec object
        IvParameterSpec ivParameterSpec = createIvParameterSpec(iv);

        // Create a Cipher object
        Cipher cipher1 = createCipher(secretKey, ivParameterSpec, Cipher.ENCRYPT_MODE);
        Cipher cipher2 = createCipher(secretKey, ivParameterSpec, Cipher.DECRYPT_MODE);

        // Message to be encrypted
        String message = "The answer is no";

        // Encrypt the message
        byte[] enc = encryptString(message, cipher1);

        // Decrypt the message
        String dec = decryptString(enc, cipher2);

        System.out.println("Original Message: " + message);
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(enc));
        System.out.println("Decrypted Message: " + dec);
    }

    // Helper function to create a SecretKey object
    private static SecretKey createSecretKey(String key) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 192 and 256 bits may not be available
        SecretKey secretKey = keyGen.generateKey();
        // For simplicity, we are using the provided key directly
        // In a real-world scenario, use a secure method to generate and store the key
        return javax.crypto.spec.SecretKeySpec.class.getConstructor(byte[].class).newInstance(key.getBytes(StandardCharsets.UTF_8));
    }

    // Helper function to create an IvParameterSpec object
    private static IvParameterSpec createIvParameterSpec(String iv) {
        return new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
    }

    // Helper function to create a Cipher object
    private static Cipher createCipher(SecretKey secretKey, IvParameterSpec ivParameterSpec, int mode) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(mode, secretKey, ivParameterSpec);
        return cipher;
    }
}