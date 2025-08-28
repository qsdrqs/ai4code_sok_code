import java.util.Arrays;

public class Main {

    /**
     * Encrypts a given string using a Caesar cipher with the provided key.
     * 
     * @param str  The string to be encrypted.
     * @param key  The shift key for the Caesar cipher.
     * @return     The encrypted string as a character array.
     */
    public static char[] encrypt(String str, int key) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        char[] newString = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                newString[i] = ' ';
                continue;
            }
            int alphaIndex = alpha.indexOf(c);
            if (alphaIndex + key > 25) {
                alphaIndex = (alphaIndex + key) % 26;
            } else {
                alphaIndex += key;
            }
            newString[i] = alpha.charAt(alphaIndex);
        }
        return newString;
    }

    /**
     * Decrypts a given string using a Caesar cipher with the provided key.
     * 
     * @param str  The string to be decrypted.
     * @param key  The shift key for the Caesar cipher.
     * @return     The decrypted string as a character array.
     */
    public static char[] decrypt(char[] str, int key) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        char[] newString = new char[str.length];
        for (int i = 0; i < str.length; i++) {
            char c = str[i];
            if (c == ' ') {
                newString[i] = ' ';
                continue;
            }
            int alphaIndex = alpha.indexOf(c);
            newString[i] = alpha.charAt((alphaIndex - key + 26) % 26);
        }
        return newString;
    }

    public static void main(String[] args) {
        char[] encrypted = encrypt("hello world", 3);
        System.out.println(Arrays.toString(encrypted));
        char[] decrypted = decrypt(encrypted, 3);
        System.out.println(Arrays.toString(decrypted));
    }
}