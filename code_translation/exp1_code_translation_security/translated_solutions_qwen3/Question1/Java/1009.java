import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.security.SecureRandom;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AESExample {

    // Register Bouncy Castle as a security provider
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        String originalMessage = "secret data";

        // Generate 16-byte key and nonce
        byte[] key = generateRandomBytes(16);
        byte[] nonce = generateRandomBytes(16);

        System.out.println("Original Message: " + originalMessage);

        // Encrypt the message
        byte[][] encrypted = encodeString(originalMessage, key, nonce);

        // Decrypt the message
        String decrypted = decodeString(encrypted, key, nonce);

        System.out.println("Decrypted Message: " + decrypted);
    }

    /**
     * Encrypts a string using AES in EAX mode.
     *
     * @param message The plaintext message to encrypt.
     * @param key     The 16-byte AES key.
     * @param nonce   The 16-byte nonce.
     * @return A 2D byte array where [0] is the ciphertext and [1] is the authentication tag.
     * @throws Exception If an error occurs during encryption.
     */
    public static byte[][] encodeString(String message, byte[] key, byte[] nonce) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/EAX", "BC");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(nonce);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] plaintextBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] ciphertext = cipher.doFinal(plaintextBytes);
        byte[] tag = cipher.getMac();

        return new byte[][]{ciphertext, tag};
    }

    /**
     * Decrypts a message using AES in EAX mode and verifies the authentication tag.
     *
     * @param p     A 2D byte array where [0] is the ciphertext and [1] is the authentication tag.
     * @param key   The 16-byte AES key.
     * @param nonce The 16-byte nonce.
     * @return The decrypted plaintext string.
     * @throws Exception If decryption fails or the tag is invalid.
     */
    public static String decodeString(byte[][] p, byte[] key, byte[] nonce) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/EAX", "BC");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(nonce);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(p[0]);
        byte[] computedTag = cipher.getMac();

        if (!Arrays.equals(computedTag, p[1])) {
            throw new RuntimeException("Authentication failed: Invalid tag");
        }

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /**
     * Generates a random byte array of the specified length.
     *
     * @param length The number of random bytes to generate.
     * @return A byte array filled with random data.
     */
    private static byte[] generateRandomBytes(int length) {
        byte[] bytes = new byte[length];
        new SecureRandom().nextBytes(bytes);
        return bytes;
    }
}