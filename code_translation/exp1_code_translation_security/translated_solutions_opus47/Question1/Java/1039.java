// Required Maven dependency:
// <dependency>
//     <groupId>com.macasaet.fernet</groupId>
//     <artifactId>fernet-java8</artifactId>
//     <version>1.5.0</version>
// </dependency>

import com.macasaet.fernet.Key;
import com.macasaet.fernet.StringValidator;
import com.macasaet.fernet.Token;
import com.macasaet.fernet.Validator;

import java.time.Duration;
import java.time.temporal.TemporalAmount;

public class FernetCrypto {

    public static String encrypt(String message, String key) {
        Key fernetKey = new Key(key);
        Token token = Token.generate(fernetKey, message);
        return token.serialise();
    }

    public static String decrypt(String message, String key) {
        Key fernetKey = new Key(key);
        Token token = Token.fromString(message);
        Validator<String> validator = new StringValidator() {
            @Override
            public TemporalAmount getTimeToLive() {
                // Python's Fernet doesn't enforce TTL by default; using a long TTL to mimic this.
                return Duration.ofDays(365 * 100);
            }
        };
        return token.validateAndDecrypt(fernetKey, validator);
    }
}