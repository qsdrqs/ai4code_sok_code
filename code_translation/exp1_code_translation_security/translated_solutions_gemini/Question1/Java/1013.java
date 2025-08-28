import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

// --- Dependencies ---
// This code requires the 'fernet-java' library.
// If you are using Maven, add this to your pom.xml:
//
// <dependency>
//     <groupId>com.macasaet.fernet</groupId>
//     <artifactId>fernet-java8</artifactId>
//     <version>1.5.0</version>
// </dependency>
//
// Or for Gradle, add to your build.gradle:
// implementation 'com.macasaet.fernet:fernet-java8:1.5.0'
import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;
import com.macasaet.fernet.Validator;

public class PasswordBasedFernet {

    private static final int DEFAULT_ITERATIONS = 100_000;
    private static final int SALT_SIZE_BYTES = 16;
    private static final int ITERATIONS_SIZE_BYTES = 4;

    /**
     * Derives a secret key from a given password and salt using PBKDF2-HMAC-SHA256.
     * The result is a URL-safe Base64 encoded string, as required by the Fernet spec.
     *
     * @param password   The password to derive the key from.
     * @param salt       A random salt.
     * @param iterations The number of iterations for PBKDF2.
     * @return A URL-safe Base64 encoded key string.
     */
    private static Key deriveKey(String password, byte[] salt, int iterations) {
        try {
            // PBKDF2 with HMAC-SHA256, 256 bits (32 bytes) key length
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] keyBytes = skf.generateSecret(spec).getEncoded();

            // Fernet keys are URL-safe Base64 encoded.
            String keyString = Base64.getUrlEncoder().encodeToString(keyBytes);
            return new Key(keyString);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // These exceptions should not happen with standard algorithms
            throw new RuntimeException("Error deriving key", e);
        }
    }

    /**
     * Encrypts a message with a password.
     * The output format is: Base64(salt + iterations + Fernet_ciphertext)
     *
     * @param message  The plaintext message to encrypt.
     * @param password The password to use for encryption.
     * @return A URL-safe Base64 encoded byte array containing the encrypted token.
     */
    public static byte[] encrypt(String message, String password) {
        return encrypt(message, password, DEFAULT_ITERATIONS);
    }

    public static byte[] encrypt(String message, String password, int iterations) {
        // 1. Generate a cryptographically secure random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE_BYTES];
        random.nextBytes(salt);

        // 2. Derive the encryption key from the password and salt
        Key key = deriveKey(password, salt, iterations);

        // 3. Encrypt the message using Fernet
        // The result of token.serialise() is already Base64 encoded.
        Token token = Token.generate(key, message);
        String serializedToken = token.serialise();

        // 4. The Python code decodes the Fernet output before prepending salt/iterations.
        // We must do the same to be compatible.
        byte[] rawFernetCiphertext = Base64.getUrlDecoder().decode(serializedToken);

        // 5. Convert iterations to a 4-byte big-endian array
        byte[] iterBytes = ByteBuffer.allocate(ITERATIONS_SIZE_BYTES).putInt(iterations).array();

        // 6. Concatenate: salt + iterations + raw_fernet_ciphertext
        ByteBuffer payload = ByteBuffer.allocate(salt.length + iterBytes.length + rawFernetCiphertext.length);
        payload.put(salt);
        payload.put(iterBytes);
        payload.put(rawFernetCiphertext);

        // 7. Base64 encode the final concatenated payload
        return Base64.getUrlEncoder().encode(payload.array());
    }

    /**
     * Decrypts a token that was encrypted with the encrypt method.
     *
     * @param token    The encrypted token (URL-safe Base64 encoded bytes).
     * @param password The password used for encryption.
     * @return The decrypted plaintext message as a String.
     */
    public static String decrypt(byte[] token, String password) {
        // 1. Decode the outer Base64 layer
        byte[] decoded = Base64.getUrlDecoder().decode(token);
        ByteBuffer wrapper = ByteBuffer.wrap(decoded);

        // 2. Extract salt, iterations, and the inner Fernet token
        byte[] salt = new byte[SALT_SIZE_BYTES];
        wrapper.get(salt);

        int iterations = wrapper.getInt(); // ByteBuffer is big-endian by default

        byte[] rawFernetCiphertext = new byte[wrapper.remaining()];
        wrapper.get(rawFernetCiphertext);

        // 3. Re-derive the key using the extracted salt and iterations
        Key key = deriveKey(password, salt, iterations);

        // 4. The Fernet library expects a Base64 encoded token, so we must re-encode
        // the raw ciphertext part we extracted.
        String fernetTokenString = Base64.getUrlEncoder().encodeToString(rawFernetCiphertext);
        Token fernetToken = Token.fromString(fernetTokenString);

        // 5. Decrypt using a validator (standard practice for fernet-java)
        // This will throw an exception if the token is invalid (e.g., wrong key, tampered).
        Validator<String> validator = new StringValidator();
        return fernetToken.validateAndDecrypt(key, validator);
    }

    /**
     * A simple validator for fernet-java that returns the payload as a UTF-8 String.
     */
    private static class StringValidator implements Validator<String> {
        @Override
        public String validateAndDecrypt(byte[] payload) {
            return new String(payload, StandardCharsets.UTF_8);
        }
    }

    public static void main(String[] args) {
        String message = "nice123";
        String password = "password123";

        // Encrypt the message
        byte[] token = encrypt(message, password);

        // Print the token as a string (UTF-8 is standard for Base64)
        System.out.println(new String(token, StandardCharsets.UTF_8));

        // Decrypt the token and print the original message
        String decryptedMessage = decrypt(token, password);
        System.out.println(decryptedMessage);
    }
}