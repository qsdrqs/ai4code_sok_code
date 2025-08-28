import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Base64;

public class Main {

    private static final int AES_KEY_SIZE = 128; // 16 bytes
    private static final int GCM_IV_LENGTH = 12; // 12 bytes
    private static final int GCM_TAG_LENGTH = 128; // 128 bits

    public static void main(String[] args) throws Exception {
        // Generate or provide a 16-byte key
        byte[] key = "Sixteen byte key".getBytes();

        // Original message
        byte[] msg = "message".getBytes();

        // Encrypt the message
        byte[][] encrypted = encrypt(key, msg);
        byte[] ciphertext = encrypted[0];
        byte[] tag = encrypted[1];
        byte[] nonce = encrypted[2];

        // Decrypt the message
        byte[] decrypted = decrypt(key, ciphertext, nonce, tag);
        System.out.println(new String(decrypted));
    }

    /**
     * Encrypts a message using AES in GCM mode.
     *
     * @param key  the encryption key
     * @param msg  the message to encrypt
     * @return an array containing the ciphertext, tag, and nonce
     * @throws Exception if an error occurs during encryption
     */
    public static byte[][] encrypt(byte[] key, byte[] msg) throws Exception {
        // Create a new AES cipher in GCM mode
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        SecureRandom secureRandom = new SecureRandom();
        byte[] nonce = new byte[GCM_IV_LENGTH];
        secureRandom.nextBytes(nonce);
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, nonce);
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

        // Encrypt the message
        byte[] ciphertext = cipher.doFinal(msg);

        // Return the ciphertext, tag, and nonce
        byte[] tag = cipher.getIV();
        return new byte[][]{ciphertext, tag, nonce};
    }

    /**
     * Decrypts a message using AES in GCM mode.
     *
     * @param key      the decryption key
     * @param ciphertext the encrypted message
     * @param nonce     the nonce used during encryption
     * @param tag       the tag used during encryption
     * @return the decrypted message
     * @throws Exception if an error occurs during decryption
     */
    public static byte[] decrypt(byte[] key, byte[] ciphertext, byte[] nonce, byte[] tag) throws Exception {
        // Create a new AES cipher in GCM mode
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec parameterSpec = new GCMParameterSpec(GCM_TAG_LENGTH, nonce);
        SecretKey secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);

        // Decrypt the message
        return cipher.doFinal(ciphertext);
    }
}