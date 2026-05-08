import com.macasaet.fernet.Key;
import com.macasaet.fernet.StringValidator;
import com.macasaet.fernet.Token;
import com.macasaet.fernet.Validator;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.temporal.TemporalAmount;

public class FernetEncryption {

    // Generate a Fernet key (equivalent to Fernet.generate_key())
    private static final Key key = Key.generateKey(new SecureRandom());

    public static String encryptText(String plainText) {
        Token token = Token.generate(new SecureRandom(), key, plainText);
        return token.serialise();
    }

    public static String decryptText(String encryptedText) {
        Token token = Token.fromString(encryptedText);
        // Custom validator with a very large TTL to match Python's Fernet
        // (Python's Fernet has no TTL check by default, but fernet-java8 defaults to 60s)
        Validator<String> validator = new StringValidator() {
            @Override
            public TemporalAmount getTimeToLive() {
                return Duration.ofDays(Integer.MAX_VALUE);
            }
        };
        return token.validateAndDecrypt(key, validator);
    }

    public static void main(String[] args) {
        String original = "Hello, World!";
        
        String encrypted = encryptText(original);
        System.out.println("Encrypted: " + encrypted);
        
        String decrypted = decryptText(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}