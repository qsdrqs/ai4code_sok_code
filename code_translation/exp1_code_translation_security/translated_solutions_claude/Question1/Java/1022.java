import com.macasaet.fernet.Key;
import com.macasaet.fernet.Token;
import java.nio.charset.StandardCharsets;

public class FernetExample {
    
    // Generate a key (equivalent to Fernet.generate_key())
    private static final Key exKey = Key.generateKey();
    
    public static Token encrypt(String inp, Key key) {
        return Token.generate(key, inp);
    }
    
    public static String decrypt(Token inp, Key key) {
        return inp.validateAndDecrypt(key, StandardCharsets.UTF_8);
    }
    
    public static void main(String[] args) {
        Token encrypted = encrypt("Hello World!", exKey);
        System.out.println(encrypted);
        
        String decrypted = decrypt(encrypted, exKey);
        System.out.println(decrypted);
    }
}