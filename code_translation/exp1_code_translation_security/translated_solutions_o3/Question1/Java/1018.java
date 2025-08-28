import java.nio.charset.StandardCharsets;
import java.time.Duration;

import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;

/**
 * A tiny helper that reproduces the behaviour of the original Python code
 * which uses cryptography.fernet.Fernet for symmetric encryption.
 *
 * Dependencies (already declared for you):
 *    <dependency>
 *        <groupId>com.macasaet</groupId>
 *        <artifactId>fernet-java8</artifactId>
 *        <version>1.5.0</version>
 *    </dependency>
 */
public final class FernetUtils {

    private FernetUtils() { /* utility class – do not instantiate */ }

    /**
     * Encrypt a plain-text string with the supplied Fernet key.
     *
     * @param input plain text that must be encrypted
     * @param key   32-byte, url-safe Base64-encoded Fernet key
     * @return the encrypted token (Base64 / url-safe string)
     */
    public static String encrypt(final String input, final String key) {
        Key fernetKey = new Key(key.getBytes(StandardCharsets.US_ASCII));
        // create a signed & encrypted Fernet token
        Token token = Token.generate(fernetKey, input.getBytes(StandardCharsets.UTF_8));
        return token.serialise();           // equivalent to Python's fernet.encrypt(...)
    }

    /**
     * Decrypt a Fernet token with the given key.
     *
     * @param token a Fernet token produced by {@link #encrypt(String, String)}
     * @param key   the same 32-byte key that was used for encryption
     * @return the original plain-text message
     */
    public static String decrypt(final String token, final String key) {
        Key fernetKey = new Key(key.getBytes(StandardCharsets.US_ASCII));

        // read token back, validate its HMAC and decrypt the payload
        byte[] message =
            Token.fromString(token)
                 // TTL is optional; here we accept tokens up to 1 hour old
                 .validateAndDecrypt(fernetKey, Duration.ofHours(1));

        return new String(message, StandardCharsets.UTF_8);  // → same as Python's fernet.decrypt(...)
    }
}