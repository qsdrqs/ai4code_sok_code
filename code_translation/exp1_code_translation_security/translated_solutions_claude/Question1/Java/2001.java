import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;

public class FernetEncryption {
    
    // Function to encrypt msg with key using Fernet (AES-128-CBC with HMAC)
    public static String encrypt(String msg, String keyString) {
        Key key = new Key(keyString);
        Token token = Token.generate(key, msg);
        return token.serialise();
    }
    
    // Function to decrypt msg with key using Fernet (AES-128-CBC with HMAC)
    public static String decrypt(String ciphertext, String keyString) {
        Key key = new Key(keyString);
        Token token = Token.fromString(ciphertext);
        return token.validateAndDecrypt(key, java.time.Instant.now());
    }
    
    // Overloaded methods for byte array inputs (closer to Python's byte handling)
    public static byte[] encrypt(byte[] msg, byte[] keyBytes) {
        Key key = new Key(keyBytes);
        Token token = Token.generate(key, new String(msg));
        return token.serialise().getBytes();
    }
    
    public static byte[] decrypt(byte[] ciphertext, byte[] keyBytes) {
        Key key = new Key(keyBytes);
        Token token = Token.fromString(new String(ciphertext));
        return token.validateAndDecrypt(key, java.time.Instant.now()).getBytes();
    }
}