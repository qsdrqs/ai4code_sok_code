import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    // Function to encrypt a given string using a given symmetric key.
    public static byte[] encrypt(SecretKey key, String plaintext) throws Exception {
        // Generate a random 96-bit IV.
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[12];
        random.nextBytes(iv);

        // Create a GCMParameterSpec
        GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);

        // Create a Cipher instance
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key, parameterSpec);

        // Encrypt the plaintext
        byte[] encryptedText = cipher.doFinal(plaintext.getBytes());

        // Combine the IV and encrypted text into a single byte array
        ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + encryptedText.length);
        byteBuffer.put(iv);
        byteBuffer.put(encryptedText);
        return byteBuffer.array();
    }

    // Function to decrypt a given string using a given symmetric key.
    public static String decrypt(SecretKey key, byte[] ciphertext) throws Exception {
        // Separate the IV and encrypted text
        ByteBuffer byteBuffer = ByteBuffer.wrap(ciphertext);
        byte[] iv = new byte[12];
        byteBuffer.get(iv);
        byte[] encryptedText = new byte[byteBuffer.remaining()];
        byteBuffer.get(encryptedText);

        // Create a GCMParameterSpec
        GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);

        // Create a Cipher instance
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, key, parameterSpec);

        // Decrypt the ciphertext
        byte[] decryptedText = cipher.doFinal(encryptedText);

        // Return the decrypted text as a string
        return new String(decryptedText);
    }

    public static void main(String[] args) throws Exception {
        // Generate a secret key
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128-bit key
        SecretKey secretKey = keyGen.generateKey();

        // Test the encrypt and decrypt functions
        String plaintext = "Hello, World!";
        byte[] ciphertext = encrypt(secretKey, plaintext);
        String decryptedText = decrypt(secretKey, ciphertext);

        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + Base64.getEncoder().encodeToString(ciphertext));
        System.out.println("Decrypted Text: " + decryptedText);
    }
}