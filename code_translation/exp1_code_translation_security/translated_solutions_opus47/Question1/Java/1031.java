/*
 * Maven dependency (fernet-java8):
 * <dependency>
 *     <groupId>com.macasaet.fernet</groupId>
 *     <artifactId>fernet-java8</artifactId>
 *     <version>1.5.0</version>
 * </dependency>
 */

import com.macasaet.fernet.Key;
import com.macasaet.fernet.StringValidator;
import com.macasaet.fernet.Token;
import com.macasaet.fernet.Validator;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.temporal.TemporalAmount;

public class FernetExample {

    private static Key fernet;
    private static final SecureRandom random = new SecureRandom();

    // Validator that accepts tokens of any age (matches Python's default Fernet behavior, which has no TTL)
    private static final Validator<String> validator = new StringValidator() {
        @Override
        public TemporalAmount getTimeToLive() {
            return Duration.ofSeconds(Long.MAX_VALUE);
        }
    };

    /**
     * encrypt using a symmetric key
     */
    public static String encrypt(String message) {
        Token encMessage = Token.generate(random, fernet, message);
        return encMessage.serialise();
    }

    /**
     * decrypt using a symmetric key
     */
    public static String decrypt(String encMessage) {
        Token token = Token.fromString(encMessage);
        String decMessage = token.validateAndDecrypt(fernet, validator);
        return decMessage;
    }

    public static void main(String[] args) {
        String message = "the solutions are here somewhere";
        System.out.println("Secret Message: " + message);

        // Equivalent to: key = Fernet.generate_key(); fernet = Fernet(key)
        fernet = Key.generateKey(random);

        String encMessage = encrypt(message);
        System.out.println(encMessage);

        String decMessage = decrypt(encMessage);
        System.out.println("decrypted Message: " + decMessage);
    }
}