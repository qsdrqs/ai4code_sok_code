import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;
import com.macasaet.fernet.Validator;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;

public class FernetExample {

    /**
     * Encrypts a message using a Fernet key.
     *
     * @param msg The message to encrypt, as a byte array.
     * @param key The Fernet key.
     * @return The encrypted message as a byte array (containing a Base64 URL-safe string).
     */
    public static byte[] encrypt(byte[] msg, Key key) {
        // In the fernet-java library, a Token represents the encrypted payload.
        // We generate a token from the key and the message.
        // The library expects the message as a String, so we convert our bytes.
        final String messageString = new String(msg, StandardCharsets.UTF_8);
        final Token token = Token.generate(key, messageString);

        // The serialized token is the final encrypted, URL-safe Base64 string.
        // We return its byte representation to match the Python function's output type.
        return token.serialise().getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Decrypts a message using a Fernet key.
     *
     * @param encryptedMsg The encrypted message as a byte array.
     * @param key The Fernet key used for encryption.
     * @return The original decrypted message as a byte array.
     */
    public static byte[] decrypt(byte[] encryptedMsg, Key key) {
        // Convert the encrypted byte array back to the serialized string format.
        final String serializedToken = new String(encryptedMsg, StandardCharsets.UTF_8);
        final Token token = Token.fromString(serializedToken);

        // The validator is used to check the token's timestamp to prevent replay attacks.
        // This is a required step in the fernet-java library.
        // We'll create a simple validator that allows a 60-second clock skew.
        final Validator<String> validator = new Validator<String>() {
            @Override
            public TemporalAmount getTimeToLive() {
                // This specifies how long a token is valid.
                return Duration.ofSeconds(60);
            }

            // The following methods can be used for custom validation logic.
            // For this example, the defaults are sufficient.
            @Override
            public void accept(String s, Instant instant) {
                // No custom validation needed here.
            }

            @Override
            public String apply(String s) {
                return s;
            }
        };

        // Validate and decrypt the token. The payload is returned as a String.
        final String decryptedString = token.validateAndDecrypt(key, validator);

        // Return the byte representation of the decrypted string.
        return decryptedString.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Main method to demonstrate encryption and decryption.
     */
    public static void main(String[] args) {
        // Generate a new secret key. This is equivalent to Fernet.generate_key().
        final Key key = Key.generateKey();

        // The message to be encrypted.
        final byte[] msg = "a much longer message with punctuation!".getBytes(StandardCharsets.UTF_8);

        // Encrypt the message.
        final byte[] encrypted = encrypt(msg, key);

        // Decrypt the message.
        final byte[] decrypted = decrypt(encrypted, key);

        // Print the results.
        // The encrypted message is a Base64 string, so we can print it as such.
        System.out.println("Encrypted: " + new String(encrypted, StandardCharsets.UTF_8));
        
        // The decrypted message should match the original.
        System.out.println("Decrypted: " + new String(decrypted, StandardCharsets.UTF_8));
    }
}