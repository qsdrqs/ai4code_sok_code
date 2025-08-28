import java.util.*;

public class CaesarCipher {
    
    public static List<String> encrypt(String str, int key) {
        String[] alpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", 
                         "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        List<String> newString = new ArrayList<>();
        
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                newString.add(" ");
                continue;
            }
            
            int alphaIndex = Arrays.asList(alpha).indexOf(String.valueOf(c));
            System.out.println(alphaIndex);
            
            if (alphaIndex + key > alpha.length - 1) {
                alphaIndex = key - (alpha.length - 1 - alphaIndex);
            }
            newString.add(alpha[alphaIndex + key]);
        }
        
        return newString;
    }
    
    public static List<String> decrypt(List<String> str, int key) {
        String[] alpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", 
                         "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        List<String> newString = new ArrayList<>();
        
        for (String c : str) {
            if (c.equals(" ")) {
                continue;
            }
            
            int alphaIndex = Arrays.asList(alpha).indexOf(c);
            newString.add(alpha[alphaIndex - key]);
        }
        
        return newString;
    }
    
    public static void main(String[] args) {
        List<String> encrypted = encrypt("hello worlz", 3);
        System.out.println(encrypted);
        List<String> decrypted = decrypt(encrypted, 3);
        System.out.println(decrypted);
    }
}