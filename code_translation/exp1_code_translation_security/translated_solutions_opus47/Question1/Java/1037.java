import com.macasaet.fernet.Key;
import com.macasaet.fernet.StringValidator;
import com.macasaet.fernet.Token;
import com.macasaet.fernet.Validator;

public class Main {

    public static Token encrypt(String input, Key key) {
        return Token.generate(key, input);
    }

    public static String decrypt(Token input, Key key) {
        Validator<String> validator = new StringValidator() {};
        return input.validateAndDecrypt(key, validator);
    }

    public static void main(String[] args) {
        Key key = Key.generateKey();
        String input = "super secret";
        Token encryptedInput = encrypt(input, key);
        String decryptedInput = decrypt(encryptedInput, key);
        System.out.println(input);
        System.out.println(decryptedInput);
    }
}