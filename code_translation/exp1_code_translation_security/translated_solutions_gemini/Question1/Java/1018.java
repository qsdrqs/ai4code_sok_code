import com.macasa.fernet.Key;
import com.macasa.fernet.Token;
import com.macasa.fernet.Validator;
import com.macasa.fernet.StringValidator;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.temporal.TemporalAmount;

/**
 * A Java utility class for Fernet encryption and decryption, equivalent to the Python example.
 * This implementation uses the 'fernet-java' library.
 *
 * To use this code, you would need the following Maven dependency:
 * <dependency>
 *     <groupId>com.macasa.fernet</groupId>
 *     <artifactId>fernet-java8</artifactId>
 *     <version>1.5.0</version>
 * </dependency>
 */
public class FernetUtils {

    // A secure random generator is needed for token generation.
    private static final SecureRandom random = new SecureRandom();

    /**
     * Encrypts a String message using a Fernet key.
     *
     * @param input The plaintext String to encrypt.
     * @param keyBytes The 32-byte Fernet key.
     * @return The encrypted message as a byte array (UTF-8 encoded token).
     */
    public static byte[] encrypt(String input, byte[] keyBytes) {
        final Key key = new Key(keyBytes);
        final byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

        // Create a token containing the data, encrypted and signed with the key.
        final Token token = Token.generate(random, key, inputBytes);

        // The serialized token is a URL-safe Base64 encoded string.
        // We return its bytes to match the Python function's return type.
        return token.serialise().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Decrypts a Fernet token using the corresponding key.
     *
     * @param input The encrypted byte array (UTF-8 encoded token).
     * @param keyBytes The 32-byte Fernet key used for encryption.
     * @return The original decrypted String.
     */
    public static String decrypt(byte[] input, byte[] keyBytes) {
        final Key key = new Key(keyBytes);
        final String serializedToken = new String(input, StandardCharsets.UTF_8);

        // The token is parsed from its serialized string form.
        final Token token = Token.fromString(serializedToken);

        // A validator is used to check the token's validity, especially its timestamp.
        // The Python cryptography library defaults to a 60-second Time-To-Live (TTL).
        // We create a validator that enforces the same rule.
        final Validator<String> validator = new StringValidator() {
            @Override
            public TemporalAmount getTimeToLive() {
                return Duration.ofSeconds(60);
            }
        };

        // The token is validated and its payload decrypted.
        // An exception is thrown if the token is invalid (e.g., expired, wrong key, corrupted).
        return token.validateAndDecrypt(key, validator);
    }
}