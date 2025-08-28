/*
 *  Uses:  groupId = "com.macasaet.fernet"
 *         artifactId = "fernet-java8"
 *         (or any other Fernet implementation you prefer)
 */

import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;

import java.time.Duration;
import java.time.Instant;
import java.nio.charset.StandardCharsets;

/**
 * Utility class that offers the same two methods that existed in the
 * original Python snippet: encrypt(...) and decrypt(...).
 */
public final class FernetUtils {

    private FernetUtils() { /* utility class – prevent instantiation */ }

    /**
     * Encrypt a message with the supplied Fernet key
     * (AES-128-CBC + HMAC-SHA256, identical to Python’s cryptography.Fernet).
     *
     * @param msg  plaintext (as bytes)
     * @param key  32-byte, URL-safe Base64 encoded key
     * @return     Fernet token (URL-safe Base64) as bytes
     */
    public static byte[] encrypt(final byte[] msg, final String key) {
        Key fernetKey = new Key(key);

        // Current timestamp – mirrors Fernet behaviour in Python
        Token token = Token.generate(Instant.now(), fernetKey, msg);

        // Library gives us a String; return bytes so the Java
        // signature matches the original Python function.
        return token.serialise().getBytes(StandardCharsets.US_ASCII);
    }

    /**
     * Decrypt a Fernet token with the supplied key.
     *
     * @param ciphertext Fernet token returned by {@link #encrypt}
     * @param key        same key that was used for encryption
     * @return           plaintext (bytes)
     */
    public static byte[] decrypt(final byte[] ciphertext, final String key) {
        Key fernetKey = new Key(key);

        String tokenString =
                new String(ciphertext, StandardCharsets.US_ASCII);

        Token token = Token.fromString(tokenString);

        /*  validateAndDecrypt() can optionally enforce a TTL.
         *  Passing a huge duration effectively disables the TTL check,
         *  which is the same behaviour the Python helper had.
         */
        return token.validateAndDecrypt(
                fernetKey,
                Duration.ofSeconds(Long.MAX_VALUE)   // no expiry check
        );
    }

    /* ---------------------------------------------------------------
     * Example main() – remove if you don’t need a test harness
     * --------------------------------------------------------------- */
    public static void main(String[] args) {
        Key generatedKey = Key.generateKey();               // create a key
        String keyString  = generatedKey.serialise();

        byte[] plaintext = "Hello Fernet!".getBytes(StandardCharsets.UTF_8);

        byte[] cipher = encrypt(plaintext, keyString);      // encrypt
        byte[] plain  = decrypt(cipher,    keyString);      // decrypt

        System.out.println(new String(plain, StandardCharsets.UTF_8));
        // → Hello Fernet!
    }
}