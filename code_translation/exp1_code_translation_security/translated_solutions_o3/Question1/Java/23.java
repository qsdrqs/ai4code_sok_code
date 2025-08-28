public class PseudoCipher {

    // Encrypts text by adding the character codes of the text and key
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char encryptedChar = (char) (text.charAt(i) + key.charAt(i));
            result.append(encryptedChar);
        }
        return result.toString();
    }

    // Decrypts text by subtracting the character codes of the key from the text
    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char decryptedChar = (char) (text.charAt(i) - key.charAt(i));
            result.append(decryptedChar);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String key       = "aaaaaaa";
        String decrypted = "abcdefg";

        String encrypted = encrypt(decrypted, key);

        System.out.println(encrypted);              // prints the encrypted text
        System.out.println(decrypt(encrypted, key)); // prints the original text after decryption
    }
}