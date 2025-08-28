import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

/**
 *  Simple example that shows how to do the same thing the Python snippet does,
 *  only in Java.  
 *
 *  Required runtime dependency (already provided to you according to the task):
 *      <groupId>com.macasaet</groupId>
 *      <artifactId>fernet-java8</artifactId>
 *      <version>…</version>
 */
public class FernetDemo {

    /* ------------------------------------------------------------------ */
    /* Helpers that mirror the Python functions                           */
    /* ------------------------------------------------------------------ */

    /** Equivalent to Fernet.generate_key() in the python library. */
    private static Key generateKey() {
        return Key.generateKey();      // 256-bit key encoded the Fernet way
    }

    /** Equivalent to the encrypt(...) function in the python code. */
    private static String encrypt(String plainText, Key key) {
        Token token = Token.generate(plainText.getBytes(StandardCharsets.UTF_8), key);
        return token.serialise();      // 100 % string safe to print / store
    }

    /** Equivalent to the decrypt(...) function in the python code. */
    private static String decrypt(String tokenString, Key key) {
        Token token  = Token.fromString(tokenString);

        /*  Python’s cryptography‐fernet checks token age as well.
            Here we give it any TTL we like (1 hour in this case).            */
        byte[] bytes = token.validateAndDecrypt(
                key,
                Duration.ofHours(1),  // time-to-live
                Instant.now());       // point in time used for the TTL test

        return new String(bytes, StandardCharsets.UTF_8);
    }

    /* ------------------------------------------------------------------ */
    /* Demo / “__main__”                                                  */
    /* ------------------------------------------------------------------ */
    public static void main(String[] args) {

        // Same three lines the python example had ------------------------
        Key key         = generateKey();
        String encrypted = encrypt("Hello World!", key);
        System.out.println(encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println(decrypted);
    }
}