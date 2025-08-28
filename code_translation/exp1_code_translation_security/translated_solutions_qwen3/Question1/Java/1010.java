import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;

public class AESEAXExample {

    // Static initializer to register Bouncy Castle provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * Class to hold the result of encryption: ciphertext and nonce.
     */
    public static class EncryptionResult {
        public final byte[] ciphertext;
        public final byte[] nonce;

        public EncryptionResult(byte[] ciphertext, byte[] nonce) {
            this.ciphertext = ciphertext;
            this.nonce = nonce;
        }
    }

    /**
     * Generates a 16-byte symmetric AES key.
     */
    public static byte[] generateKey() {
        byte[] key = new byte[16];
        new SecureRandom().nextBytes(key);
        return key;
    }

    /**
     * Encrypts a string using AES in EAX mode.
     *
     * @param key  The 16-byte AES key.
     * @param data The plaintext string to encrypt.
     * @return An EncryptionResult containing the ciphertext and nonce.
     * @throws Exception If encryption fails.
     */
    public static EncryptionResult encrypt(byte[] key, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/EAX", "BC");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] nonce = cipher.getIV();
        byte[] ciphertext = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return new EncryptionResult(ciphertext, nonce);
    }

    /**
     * Decrypts a ciphertext using AES in EAX mode.
     *
     * @param key       The 16-byte AES key.
     * @param nonce     The nonce used during encryption.
     * @param ciphertext The encrypted data (includes the authentication tag).
     * @return The decrypted plaintext as a byte array.
     * @throws Exception If decryption fails (e.g., due to invalid tag).
     */
    public static byte[] decrypt(byte[] key, byte[] nonce, byte[] ciphertext) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/EAX", "BC");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(nonce);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(ciphertext);
    }

    /**
     * Example usage of the encryption and decryption functions.
     */
    public static void main(String[] args) throws Exception {
        byte[] key = generateKey();
        String test_data = "Jim's test";

        // Encrypt
        EncryptionResult result = encrypt(key, test_data);
        System.out.println("Encrypted: " + bytesToHex(result.ciphertext));

        // Decrypt
        byte[] restored_data = decrypt(key, result.nonce, result.ciphertext);
        System.out.println("Decrypted: " + new String(restored_data, StandardCharsets.UTF_8));
    }

    /**
     * Utility method to convert a byte array to a hex string.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}