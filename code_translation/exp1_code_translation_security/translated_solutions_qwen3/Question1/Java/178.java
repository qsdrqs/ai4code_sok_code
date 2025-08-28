import java.util.ArrayList;
import java.util.List;

public class CaesarCipher {
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        List<Character> encrypted = encrypt("hello worlz", 3);
        System.out.println(encrypted);
        List<Character> decrypted = decrypt(encrypted, 3);
        System.out.println(decrypted);
    }

    public static List<Character> encrypt(String str, int key) {
        List<Character> newString = new ArrayList<>();
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                newString.add(' ');
                continue;
            }
            int alphaIndex = ALPHA.indexOf(c);
            if (alphaIndex + key > ALPHA.length() - 1) {
                alphaIndex = key - (ALPHA.length() - 1 - alphaIndex);
            }
            newString.add(ALPHA.charAt(alphaIndex + key));
        }
        return newString;
    }

    public static List<Character> decrypt(List<Character> str, int key) {
        List<Character> newString = new ArrayList<>();
        for (Character c : str) {
            if (c == ' ') {
                continue;
            }
            int alphaIndex = ALPHA.indexOf(c);
            newString.add(ALPHA.charAt(alphaIndex - key));
        }
        return newString;
    }
}