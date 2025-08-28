import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Functions that one encrypts and the other decrypts a given string using a given symmetric key
 */
public class AESCipher {

    private static final int BLOCK_SIZE = 16; // AES block size in bytes
    private static final int KEY_SIZE = 32; // AES key size in bytes (256 bits)
    private byte[] key;

    /**
     * Constructor to initialize the AES cipher with a given key.
     * 
     * @param key The symmetric key to use for encryption and decryption.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    public AESCipher(String key) throws NoSuchAlgorithmException {
        this.key = sha256(key.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Encrypts a given string using the provided symmetric key.
     * 
     * @param raw The string to encrypt.
     * @return The encrypted string in base64 format.
     * @throws Exception If an error occurs during encryption.
     */
    public String encrypt(String raw) throws Exception {
        byte[] paddedRaw = pad(raw.getBytes(StandardCharsets.UTF_8));
        byte[] iv = new byte[BLOCK_SIZE];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] encrypted = cipher.doFinal(paddedRaw);

        // Concatenate IV and encrypted data
        byte[] result = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, result, 0, iv.length);
        System.arraycopy(encrypted, 0, result, iv.length, encrypted.length);

        return Base64.getEncoder().encodeToString(result);
    }

    /**
     * Decrypts a given string using the provided symmetric key.
     * 
     * @param enc The string to decrypt in base64 format.
     * @return The decrypted string.
     * @throws Exception If an error occurs during decryption.
     */
    public String decrypt(String enc) throws Exception {
        byte[] encoded = Base64.getDecoder().decode(enc);
        byte[] iv = new byte[BLOCK_SIZE];
        System.arraycopy(encoded, 0, iv, 0, BLOCK_SIZE);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

        byte[] decrypted = cipher.doFinal(encoded, BLOCK_SIZE, encoded.length - BLOCK_SIZE);

        return new String(unpad(decrypted), StandardCharsets.UTF_8);
    }

    /**
     * Pads a given byte array to a multiple of the block size.
     * 
     * @param s The byte array to pad.
     * @return The padded byte array.
     */
    private byte[] pad(byte[] s) {
        int paddingSize = BLOCK_SIZE - (s.length % BLOCK_SIZE);
        byte[] padded = new byte[s.length + paddingSize];
        System.arraycopy(s, 0, padded, 0, s.length);
        for (int i = s.length; i < padded.length; i++) {
            padded[i] = (byte) paddingSize;
        }
        return padded;
    }

    /**
     * Unpads a given byte array.
     * 
     * @param s The byte array to unpad.
     * @return The unpadded byte array.
     */
    private byte[] unpad(byte[] s) {
        int paddingSize = s[s.length - 1];
        byte[] unpadded = new byte[s.length - paddingSize];
        System.arraycopy(s, 0, unpadded, 0, unpadded.length);
        return unpadded;
    }

    /**
     * Computes the SHA-256 hash of a given byte array.
     * 
     * @param bytes The byte array to hash.
     * @return The SHA-256 hash of the byte array.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    private byte[] sha256(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(bytes);
    }

    public static void main(String[] args) throws Exception {
        String key = "mysecretpassword";
        AESCipher cipher = new AESCipher(key);
        String encrypted = cipher.encrypt("Secret Message A");
        String decrypted = cipher.decrypt(encrypted);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}