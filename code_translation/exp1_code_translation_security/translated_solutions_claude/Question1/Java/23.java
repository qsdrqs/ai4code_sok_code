public class PseudoCipher {
    
    // this is gonna be really scuffed but it might work
    // right now text and key must be equal length, but that can be changed to loop to the front of the key again for very long text
    // it's TECHNICALLY a psuedo-cypher
    
    public static String encrypt(String text, String key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            int thisChar = (int)text.charAt(i) + (int)key.charAt(i);
            result += (char)thisChar;
        }
        return result;
    }
    
    public static String decrypt(String text, String key) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            int thisChar = (int)text.charAt(i) - (int)key.charAt(i);
            result += (char)thisChar;
        }
        return result;
    }
    
    public static void main(String[] args) {
        String key = "aaaaaaa";
        String decrypted = "abcdefg";
        String encrypted = encrypt("abcdefg", key);
        System.out.println(encrypt(decrypted, key));
        System.out.println(decrypt(encrypted, key));
    }
}