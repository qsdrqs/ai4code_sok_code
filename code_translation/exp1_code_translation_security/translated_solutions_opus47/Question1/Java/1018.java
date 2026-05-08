import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;
import com.macasaet.fernet.StringValidator;
import com.macasaet.fernet.Validator;
import java.time.Duration;
import java.time.temporal.TemporalAmount;

public class FernetEncryption {

    public static String encrypt(String input, String key) {
        Key fernetKey = new Key(key);
        Token token = Token.generate(fernetKey, input);
        return token.serialise();
    }

    public static String decrypt(String input, String key) {
        Key fernetKey = new Key(key);
        Token token = Token.fromString(input);
        Validator<String> validator = new StringValidator() {
            @Override
            public TemporalAmount getTimeToLive() {
                // Effectively disable TTL check to match Python's default behavior
                return Duration.ofDays(Integer.MAX_VALUE);
            }
        };
        return token.validateAndDecrypt(fernetKey, validator);
    }
}