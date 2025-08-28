import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    /**
     * Encrypts a message using AES in EAX mode.
     *
     * @param m  the message to encrypt
     * @param sk the secret key
     * @return a tuple containing the encrypted message and the nonce
     * @throws Exception if an error occurs during encryption
     */
    public static class EncryptionResult {
        public byte[] encryptedMessage;
        public byte[] nonce;

        public EncryptionResult(byte[] encryptedMessage, byte[] nonce) {
            this.encryptedMessage = encryptedMessage;
            this.nonce = nonce;
        }
    }

    public static EncryptionResult encrypt(byte[] m, SecretKey sk) throws Exception {
        // Generate a random 128-bit IV.
        SecureRandom random = new SecureRandom();
        byte[] iv = new byte[16];
        random.nextBytes(iv);

        // Create a Cipher instance with AES in EAX mode.
        Cipher cipher = Cipher.getInstance("AES/EAX/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, sk, new javax.crypto.spec.IvParameterSpec(iv));

        // Encrypt the message.
        byte[] encryptedMessage = cipher.doFinal(m);

        // Return the encrypted message and the IV (nonce).
        return new EncryptionResult(encryptedMessage, iv);
    }

    /**
     * Decrypts a message using AES in EAX mode.
     *
     * @param cn a tuple containing the encrypted message and the nonce
     * @param sk the secret key
     * @return the decrypted message
     * @throws Exception if an error occurs during decryption
     */
    public static byte[] decrypt(EncryptionResult cn, SecretKey sk) throws Exception {
        // Create a Cipher instance with AES in EAX mode.
        Cipher cipher = Cipher.getInstance("AES/EAX/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, sk, new javax.crypto.spec.IvParameterSpec(cn.nonce));

        // Decrypt the message.
        return cipher.doFinal(cn.encryptedMessage);
    }

    public static void main(String[] args) throws Exception {
        // Generate a secret key.
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128-bit key
        SecretKey secretKey = keyGen.generateKey();

        // Message to encrypt.
        String message = "Hello, World!";
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        // Encrypt the message.
        EncryptionResult encrypted = encrypt(messageBytes, secretKey);
        System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(encrypted.encryptedMessage));
        System.out.println("Nonce: " + Base64.getEncoder().encodeToString(encrypted.nonce));

        // Decrypt the message.
        byte[] decrypted = decrypt(encrypted, secretKey);
        System.out.println("Decrypted Message: " + new String(decrypted, StandardCharsets.UTF_8));
    }
}