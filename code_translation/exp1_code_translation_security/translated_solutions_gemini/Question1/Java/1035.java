import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

// Bouncy Castle is required for AES/EAX mode.
// Add it to your project, e.g., via Maven:
// <dependency>
//     <groupId>org.bouncycastle</groupId>
//     <artifactId>bcprov-jdk18on</artifactId>
//     <version>1.77</version>
// </dependency>
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.AEADParameterSpec;

public class AesEaxExample {

    // The key must be 16, 24, or 32 bytes long (for AES-128, AES-192, or AES-256).
    private static final byte[] KEY = "Sixteen byte key".getBytes(StandardCharsets.UTF_8);
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/EAX/NoPadding";
    private static final int NONCE_LENGTH_BYTES = 16; // EAX standard nonce size is the block size
    private static final int TAG_LENGTH_BITS = 128;   // 128-bit authentication tag

    // A simple record to hold the result of encryption, similar to a Python tuple.
    public record EncryptionResult(byte[] ciphertext, byte[] tag, byte[] nonce) {}

    /**
     * Encrypts a message using AES/EAX mode.
     *
     * @param key The secret key.
     * @param msg The message to encrypt.
     * @return An EncryptionResult containing the ciphertext, authentication tag, and nonce.
     * @throws Exception if encryption fails.
     */
    public static EncryptionResult encrypt(byte[] key, byte[] msg) throws Exception {
        // Generate a random nonce
        SecureRandom random = new SecureRandom();
        byte[] nonce = new byte[NONCE_LENGTH_BYTES];
        random.nextBytes(nonce);

        // Create AES key spec
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);

        // Create AEAD parameter spec with nonce and tag length
        AEADParameterSpec parameterSpec = new AEADParameterSpec(nonce, TAG_LENGTH_BITS);

        // Get Cipher instance and initialize it
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, parameterSpec);

        // Perform encryption. In Java's JCE, the tag is appended to the ciphertext.
        byte[] encryptedData = cipher.doFinal(msg);

        // Separate the ciphertext and the tag
        int tagLengthBytes = TAG_LENGTH_BITS / 8;
        int ciphertextLength = encryptedData.length - tagLengthBytes;

        byte[] ciphertext = Arrays.copyOfRange(encryptedData, 0, ciphertextLength);
        byte[] tag = Arrays.copyOfRange(encryptedData, ciphertextLength, encryptedData.length);

        return new EncryptionResult(ciphertext, tag, nonce);
    }

    /**
     * Decrypts a message using AES/EAX mode.
     *
     * @param key        The secret key.
     * @param ciphertext The encrypted data.
     * @param nonce      The nonce used for encryption.
     *_param tag        The authentication tag.
     * @return The original plaintext as a byte array.
     * @throws Exception if decryption or tag verification fails.
     */
    public static byte[] decrypt(byte[] key, byte[] ciphertext, byte[] nonce, byte[] tag) throws Exception {
        // Create AES key spec
        SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);

        // Create AEAD parameter spec with the same nonce and tag length
        AEADParameterSpec parameterSpec = new AEADParameterSpec(nonce, TAG_LENGTH_BITS);

        // Get Cipher instance and initialize it
        Cipher cipher = Cipher.getInstance(TRANSFORMATION, "BC");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, parameterSpec);

        // Combine ciphertext and tag for decryption, as JCE expects them together.
        byte[] combined = new byte[ciphertext.length + tag.length];
        System.arraycopy(ciphertext, 0, combined, 0, ciphertext.length);
        System.arraycopy(tag, 0, combined, ciphertext.length, tag.length);

        // Perform decryption and authentication.
        // An AEADBadTagException is thrown if the tag is invalid.
        return cipher.doFinal(combined);
    }

    public static void main(String[] args) {
        // Add Bouncy Castle as a security provider
        Security.addProvider(new BouncyCastleProvider());

        try {
            // 1. Define the message to be encrypted
            byte[] message = "message".getBytes(StandardCharsets.UTF_8);
            System.out.println("Original message: " + new String(message, StandardCharsets.UTF_8));

            // 2. Encrypt the message
            EncryptionResult result = encrypt(KEY, message);
            System.out.println("Encryption successful.");
            // Note: Printing raw byte arrays is not very readable.
            // System.out.println("Ciphertext: " + Arrays.toString(result.ciphertext()));
            // System.out.println("Tag: " + Arrays.toString(result.tag()));
            // System.out.println("Nonce: " + Arrays.toString(result.nonce()));

            // 3. Decrypt the message
            byte[] decryptedBytes = decrypt(KEY, result.ciphertext(), result.nonce(), result.tag());

            // 4. Print the decrypted result
            String decryptedMessage = new String(decryptedBytes, StandardCharsets.UTF_8);
            System.out.println("Decrypted message: " + decryptedMessage);

        } catch (Exception e) {
            System.err.println("An error occurred during the crypto operation.");
            e.printStackTrace();
        }
    }
}