public class PseudoCipher {

    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int thisChar = text.charAt(i) + key.charAt(i);
            result.append((char) thisChar);
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int thisChar = text.charAt(i) - key.charAt(i);
            result.append((char) thisChar);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String key = "aaaaaaa";
        String decrypted = "abcdefg";
        String encrypted = encrypt(decrypted, key);
        System.out.println(encrypted);
        System.out.println(decrypt(encrypted, key));
    }
}