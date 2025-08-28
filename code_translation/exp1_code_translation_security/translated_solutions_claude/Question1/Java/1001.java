import java.util.ArrayList;
import java.util.List;

public class Encryption {
    
    public static String encrypt(String key, String string) {
        List<Character> encryptedText = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            char keyC = key.charAt(i % key.length());
            char encryptedC = (char)((string.charAt(i) + keyC) % 256);
            encryptedText.add(encryptedC);
        }
        return joinChars(encryptedText);
    }
    
    public static String decrypt(String key, String string) {
        List<Character> decryptedText = new ArrayList<>();
        for (int i = 0; i < string.length(); i++) {
            char keyC = key.charAt(i % key.length());
            char decryptedC = (char)((string.charAt(i) - keyC + 256) % 256);
            decryptedText.add(decryptedC);
        }
        return joinChars(decryptedText);
    }
    
    private static String joinChars(List<Character> chars) {
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }
        return sb.toString();
    }
}