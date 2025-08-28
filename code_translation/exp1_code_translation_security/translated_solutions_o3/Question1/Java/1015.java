/*
 * Python → Java translation of:
 *
 *     import cryptography
 *     from cryptography.fernet import Fernet
 *
 *     def encrypt(data, key):
 *         ...
 *
 *     def decrypt(data, key):
 *         ...
 *
 *     def main():
 *         encrypt("/safedir/test.txt", Fernet.generate_key())
 *
 *     if __name__ == "__main__":
 *         main()
 *
 * ------------------------------------------------------------------
 * Build-time dependency (Maven notation):
 *
 *     <dependency>
 *         <groupId>com.macasaet.fernet</groupId>
 *         <artifactId>fernet-java8</artifactId>
 *         <version>1.5.2</version>
 *     </dependency>
 *
 * A different build tool (Gradle, sbt, etc.) can of course be used
 * instead; just add the same coordinates.
 */

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;

public class FernetExample {

    /* ------------------------------------------------------------------
     * Helper – create a fresh 32-byte (256-bit) key, URL-safe Base64,
     * exactly what Python’s Fernet.generate_key() returns.
     * ------------------------------------------------------------------ */
    public static String generateKey() {
        byte[] bytes = new byte[32];                    // 256-bit key
        new SecureRandom().nextBytes(bytes);            // cryptographically strong RNG
        return Base64.getUrlEncoder()
                     .withoutPadding()
                     .encodeToString(bytes);            // URL-safe base64
    }

    /* ------------------------------------------------------------------
     * Encrypt a byte[] (or String converted to byte[]) with Fernet.
     * ------------------------------------------------------------------ */
    public static String encrypt(byte[] data, String base64Key) {
        Key key   = new Key(base64Key);                 // wraps / validates key
        Token tok = Token.generate(key, data);          // create Fernet token
        return tok.serialise();                         // Base64 string – same as Python
    }

    /* ------------------------------------------------------------------
     * Decrypt a Fernet token.
     * ------------------------------------------------------------------ */
    public static byte[] decrypt(String tokenString, String base64Key) {
        Key   key   = new Key(base64Key);
        Token token = Token.fromString(tokenString);
        return token.validateAndDecrypt(key);           // returns the original plaintext
    }

    /* ------------------------------------------------------------------
     * Equivalent of the small main() function in the Python script.
     * ------------------------------------------------------------------ */
    public static void main(String[] args) {

        // Generate a brand-new random key (identical purpose to Fernet.generate_key()).
        String key = generateKey();

        // Python example encrypted the literal string "/safedir/test.txt".
        String token = encrypt("/safedir/test.txt".getBytes(StandardCharsets.UTF_8), key);

        System.out.println("Encrypted  : " + token);

        // Show that decrypt() brings the data back.
        byte[] plain = decrypt(token, key);
        System.out.println("Decrypted  : " + new String(plain, StandardCharsets.UTF_8));
    }
}