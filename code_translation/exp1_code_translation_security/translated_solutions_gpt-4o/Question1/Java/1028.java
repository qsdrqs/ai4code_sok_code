public class SymmetricEncryption {

    public static String encrypt(String input, String key) {
        StringBuilder encryptedString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            encryptedString.append((char) (input.charAt(i) ^ key.charAt(i % key.length())));
        }
        return encryptedString.toString();
    }

    public static String decrypt(String input, String key) {
        // Decryption is the same as encryption due to the symmetric nature of XOR
        StringBuilder decryptedString = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            decryptedString.append((char) (input.charAt(i) ^ key.charAt(i % key.length())));
        }
        return decryptedString.toString();
    }

    public static void main(String[] args) {
        String originalText = "Hello, World!";
        String key = "key";

        String encryptedText = encrypt(originalText, key);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted: " + decryptedText);
    }
}