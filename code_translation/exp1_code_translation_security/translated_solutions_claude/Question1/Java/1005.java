import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;
import java.nio.charset.StandardCharsets;

public class FernetEncryption {
    
    public static Token encrypt(byte[] msg, Key key) {
        return Token.generate(key, msg);
    }
    
    public static byte[] decrypt(Token token, Key key) {
        return token.validateAndDecrypt(key, java.time.Instant.now());
    }
    
    public static void main(String[] args) {
        Key key = Key.generateKey();
        byte[] msg = "a much longer message with punctuation!".getBytes(StandardCharsets.UTF_8);
        Token e = encrypt(msg, key);
        byte[] d = decrypt(e, key);
        
        System.out.println(e.serialise());
        System.out.println(new String(d, StandardCharsets.UTF_8));
    }
}