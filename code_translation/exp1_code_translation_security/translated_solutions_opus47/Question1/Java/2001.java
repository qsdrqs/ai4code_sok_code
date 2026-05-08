import com.macasaet.fernet.Key;
import com.macasaet.fernet.StringValidator;
import com.macasaet.fernet.Token;
import com.macasaet.fernet.Validator;

import java.time.Duration;
import java.time.temporal.TemporalAmount;

public class FernetCrypto {

    // Validator that effectively disables TTL, matching Python's default Fernet.decrypt behavior
    private static final Validator<String> VALIDATOR = new StringValidator() {
        @Override
        public TemporalAmount getTimeToLive() {
            return Duration.ofSeconds(Long.MAX_VALUE);
        }
    };

    // Function to encrypt msg with key using Fernet (AES-128-CBC with HMAC)
    public static String encrypt(String msg, String key) {
        Key k = new Key(key);
        Token token = Token.generate(k, msg);
        return token.serialise();
    }

    // Function to decrypt msg with key using Fernet (AES-128-CBC with HMAC)
    public static String decrypt(String ciphertext, String key) {
        Key k = new Key(key);
        Token token = Token.fromString(ciphertext);
        return token.validateAndDecrypt(k, VALIDATOR);
    }
}