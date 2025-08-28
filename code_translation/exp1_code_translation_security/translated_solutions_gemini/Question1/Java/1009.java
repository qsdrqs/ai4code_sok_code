import javax.crypto.Cipher;
import javax.crypto.spec.AEADParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.Security;

// Bouncy Castle is required for AES/EAX mode, as it's not included in the standard Java providers.
// Add the following Maven dependency to your project's pom.xml:
//
// <dependency>
//     <groupId>org.bouncycastle</groupId>
//     <artifactId>bcprov-jdk18on</artifactId>
//     <version>1.77</version> <!-- Use the latest version available -->
// </dependency>
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AesEaxExample {

    // In Java's JCE (Java Cryptography Extension), AEAD modes like EAX and GCM
    // typically return a single byte array containing the ciphertext and the authentication tag appended.
    // This differs from PyCryptodome, which returns a tuple of (ciphertext, tag).
    private static final int AUTH_TAG_LENGTH_BITS = 128; // 16 bytes, a standard size for AES.

    /**
     * Encrypts a String message using AES/EAX mode.
     *
     * @param message The String to encrypt.
     * @param key     A 16, 24, or 32-byte key.
     * @param nonce   A random nonce (Number used once), typically 16 bytes for EAX.
     * @return A single byte array containing the ciphertext and the authentication tag.
     * @throws Exception if encryption fails.
     */
    public static byte[] encodeString(String message, byte[] key, byte[] nonce) throws Exception {
        // Get a Cipher instance for AES/EAX/NoPadding from the Bouncy Castle provider
        Cipher cipher = Cipher.getInstance("AES/EAX/NoPadding", "BC");

        // Create the SecretKeySpec from the raw key bytes
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        // Create the AEADParameterSpec, which holds the nonce and the authentication tag size
        AEADParameterSpec aeadSpec = new AEADParameterSpec(nonce, AUTH_TAG_LENGTH_BITS);

        // Initialize the cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, aeadSpec);

        // Encode the string message into bytes using UTF-8
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);

        // Perform the encryption. The result includes the ciphertext and the authentication tag.
        return cipher.doFinal(messageBytes);
    }

    /**
     * Decrypts a combined ciphertext-and-tag byte array using AES/EAX mode.
     *
     * @param combinedCiphertext The byte array from encodeString (ciphertext + tag).
     * @param key                The same key used for encryption.
     * @param nonce              The same nonce used for encryption.
     * @return The original, decrypted string.
     * @throws Exception if decryption or tag verification fails.
     */
    public static String decodeString(byte[] combinedCiphertext, byte[] key, byte[] nonce) throws Exception {
        // Get a Cipher instance for AES/EAX/NoPadding from the Bouncy Castle provider
        Cipher cipher = Cipher.getInstance("AES/EAX/NoPadding", "BC");

        // Create the SecretKeySpec from the raw key bytes
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        // Create the AEADParameterSpec, which is needed for both encryption and decryption
        AEADParameterSpec aeadSpec = new AEADParameterSpec(nonce, AUTH_TAG_LENGTH_BITS);

        // Initialize the cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, keySpec, aeadSpec);

        // Perform the decryption and verification.
        // An AEADBadTagException will be thrown if the tag is invalid (tampering detected).
        byte[] decryptedBytes = cipher.doFinal(combinedCiphertext);

        // Decode the decrypted bytes back into a string using UTF-8
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        // Register Bouncy Castle as a security provider.
        // This is essential to make "AES/EAX/NoPadding" available.
        Security.addProvider(new BouncyCastleProvider());

        try {
            // Equivalent to Python's get_random_bytes(16)
            SecureRandom random = new SecureRandom();
            byte[] key = new byte[16]; // 128-bit key
            byte[] nonce = new byte[16]; // 128-bit nonce
            random.nextBytes(key);
            random.nextBytes(nonce);

            String originalMessage = "secret data";

            System.out.println("Original Message: " + originalMessage);

            // Encrypt the message
            byte[] ciphertext = encodeString(originalMessage, key, nonce);

            // In Java, the ciphertext and tag are combined.
            // For demonstration, we can see its length.
            // Ciphertext length = message length. Tag length = 16 bytes.
            System.out.println("Encrypted data length (ciphertext + tag): " + ciphertext.length);

            // Decrypt the message
            String decodedString = decodeString(ciphertext, key, nonce);

            System.out.println("Decoded Message:  " + decodedString);

            // Verify correctness
            if (originalMessage.equals(decodedString)) {
                System.out.println("\nSuccess: The decoded message matches the original.");
            } else {
                System.out.println("\nFailure: The decoded message does NOT match the original.");
            }

        } catch (Exception e) {
            System.err.println("An error occurred during the cryptographic operation.");
            e.printStackTrace();
        }
    }
}