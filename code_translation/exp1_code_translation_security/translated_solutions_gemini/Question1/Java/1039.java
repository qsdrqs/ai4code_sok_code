import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

/**
 * A Java implementation of the Fernet symmetric encryption specification.
 * This implementation uses standard Java crypto libraries and does not require
 * any external dependencies.
 *
 * @see <a href="https://github.com/fernet/spec/blob/master/Spec.md">Fernet Specification</a>
 */
public class Fernet {

    private static final String ENCRYPTION_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SIGNING_ALGORITHM = "HmacSHA256";
    private static final byte VERSION = (byte) 0x80;
    private static final int IV_SIZE = 16; // AES block size

    private final SecretKeySpec signingKey;
    private final SecretKeySpec encryptionKey;

    /**
     * Initializes a Fernet instance with a URL-safe base64-encoded key.
     *
     * @param base64Key The 32-byte key, encoded in URL-safe base64.
     * @throws InvalidKeyException if the key is not 32 bytes after decoding.
     */
    public Fernet(String base64Key) throws InvalidKeyException {
        byte[] key = Base64.getUrlDecoder().decode(base64Key);
        if (key.length != 32) {
            throw new InvalidKeyException("Fernet key must be 32 bytes long.");
        }

        // The first 16 bytes of the key are for signing, the last 16 are for encryption.
        this.signingKey = new SecretKeySpec(key, 0, 16, SIGNING_ALGORITHM);
        this.encryptionKey = new SecretKeySpec(key, 16, 16, "AES");
    }

    /**
     * Generates a new, cryptographically secure Fernet key.
     *
     * @return A URL-safe base64-encoded key string.
     */
    public static String generateKey() {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[32];
        random.nextBytes(key);
        return Base64.getUrlEncoder().encodeToString(key);
    }

    /**
     * Encrypts a plaintext message.
     *
     * @param message The message to encrypt.
     * @return A URL-safe base64-encoded Fernet token.
     */
    public String encrypt(String message) {
        try {
            // 1. Get the message bytes
            byte[] plaintext = message.getBytes(StandardCharsets.UTF_8);

            // 2. Get current timestamp (8 bytes, big-endian)
            long timestamp = System.currentTimeMillis() / 1000L;
            byte[] timestampBytes = ByteBuffer.allocate(8).putLong(timestamp).array();

            // 3. Generate a random IV (16 bytes)
            SecureRandom random = new SecureRandom();
            byte[] iv = new byte[IV_SIZE];
            random.nextBytes(iv);

            // 4. Encrypt the plaintext
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, this.encryptionKey, new IvParameterSpec(iv));
            byte[] ciphertext = cipher.doFinal(plaintext);

            // 5. Create the data to be signed
            ByteBuffer toSign = ByteBuffer.allocate(1 + 8 + IV_SIZE + ciphertext.length);
            toSign.put(VERSION);
            toSign.put(timestampBytes);
            toSign.put(iv);
            toSign.put(ciphertext);

            // 6. Sign the data with HMAC-SHA256
            Mac mac = Mac.getInstance(SIGNING_ALGORITHM);
            mac.init(this.signingKey);
            byte[] signature = mac.doFinal(toSign.array());

            // 7. Assemble the final token
            ByteBuffer token = ByteBuffer.allocate(toSign.capacity() + signature.length);
            token.put(toSign.array());
            token.put(signature);

            // 8. URL-safe Base64 encode the token
            return Base64.getUrlEncoder().encodeToString(token.array());

        } catch (Exception e) {
            // Wrap crypto exceptions in a runtime exception for convenience
            throw new RuntimeException("Failed to encrypt message", e);
        }
    }

    /**
     * Decrypts a Fernet token.
     *
     * @param token The URL-safe base64-encoded Fernet token.
     * @return The original plaintext message.
     * @throws RuntimeException if the token is invalid, corrupt, or the key is wrong.
     */
    public String decrypt(String token) {
        try {
            // 1. URL-safe Base64 decode the token
            byte[] decodedToken = Base64.getUrlDecoder().decode(token);

            // 2. Extract the signature (last 32 bytes)
            int signatureSize = 32;
            if (decodedToken.length < 1 + 8 + IV_SIZE + signatureSize) {
                throw new IllegalArgumentException("Invalid token length");
            }
            byte[] signatureFromToken = Arrays.copyOfRange(decodedToken, decodedToken.length - signatureSize, decodedToken.length);
            byte[] dataToVerify = Arrays.copyOfRange(decodedToken, 0, decodedToken.length - signatureSize);

            // 3. Verify the signature
            Mac mac = Mac.getInstance(SIGNING_ALGORITHM);
            mac.init(this.signingKey);
            byte[] calculatedSignature = mac.doFinal(dataToVerify);

            if (!MessageDigest.isEqual(signatureFromToken, calculatedSignature)) {
                throw new InvalidKeyException("Signature mismatch. Token may be tampered with or the key is incorrect.");
            }

            // 4. Extract components from the verified data
            ByteBuffer verifiedData = ByteBuffer.wrap(dataToVerify);
            byte version = verifiedData.get();
            if (version != VERSION) {
                throw new IllegalArgumentException("Unknown token version");
            }

            long timestamp = verifiedData.getLong(); // Timestamp is not validated here, but could be for TTL checks
            byte[] iv = new byte[IV_SIZE];
            verifiedData.get(iv);
            byte[] ciphertext = new byte[verifiedData.remaining()];
            verifiedData.get(ciphertext);

            // 5. Decrypt the ciphertext
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, this.encryptionKey, new IvParameterSpec(iv));
            byte[] decryptedBytes = cipher.doFinal(ciphertext);

            // 6. Convert decrypted bytes to string
            return new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt token", e);
        }
    }

    // Main method for demonstration
    public static void main(String[] args) {
        try {
            // 1. Generate a secret key (do this once and store it securely)
            String key = Fernet.generateKey();
            System.out.println("Generated Key: " + key);

            // 2. Create a Fernet instance with the key
            Fernet fernet = new Fernet(key);

            // 3. The message to be encrypted
            String originalMessage = "This is a secret message!";
            System.out.println("Original Message: " + originalMessage);

            // 4. Encrypt the message
            String encryptedMessage = fernet.encrypt(originalMessage);
            System.out.println("Encrypted Token: " + encryptedMessage);

            // 5. Decrypt the message
            String decryptedMessage = fernet.decrypt(encryptedMessage);
            System.out.println("Decrypted Message: " + decryptedMessage);

            // Verification
            if (originalMessage.equals(decryptedMessage)) {
                System.out.println("\nSuccess: The decrypted message matches the original.");
            } else {
                System.out.println("\nFailure: The messages do not match.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}