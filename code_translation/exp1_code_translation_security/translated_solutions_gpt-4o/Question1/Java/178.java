import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncryptDecrypt {

    public static List<Character> encrypt(String str, int key) {
        List<Character> alpha = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        List<Character> newString = new ArrayList<>();

        for (char c : str.toCharArray()) {
            if (c == ' ') {
                newString.add(' ');
                continue;
            }
            int alphaIndex = alpha.indexOf(c);
            System.out.println(alphaIndex);
            if (alphaIndex + key > alpha.size() - 1) {
                alphaIndex = key - (alpha.size() - 1 - alphaIndex);
            }
            newString.add(alpha.get(alphaIndex + key));
        }

        return newString;
    }

    public static List<Character> decrypt(List<Character> str, int key) {
        List<Character> alpha = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        List<Character> newString = new ArrayList<>();

        for (char c : str) {
            if (c == ' ') {
                continue;
            }
            int alphaIndex = alpha.indexOf(c);
            newString.add(alpha.get(alphaIndex - key));
        }

        return newString;
    }

    public static void main(String[] args) {
        List<Character> encrypted = encrypt("hello worlz", 3);
        System.out.println(encrypted);

        List<Character> decrypted = decrypt(encrypted, 3);
        System.out.println(decrypted);
    }
}