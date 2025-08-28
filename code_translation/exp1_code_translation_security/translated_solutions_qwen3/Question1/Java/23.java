public class SimpleCipher {

    /**
     * Encrypts the given text using a simple character addition cipher.
     * Each character in the text is added to the corresponding character in the key.
     *
     * @param text The plaintext to encrypt.
     * @param key  The key used for encryption (must be same length as text).
     * @return The encrypted string.
     */
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i);
            result.append((char) (textChar + keyChar));
        }
        return result.toString();
    }

    /**
     * Decrypts the given encrypted text using the corresponding key.
     * Each character in the encrypted text is subtracted by the corresponding character in the key.
     *
     * @param text The encrypted text to decrypt.
     * @param key  The key used for decryption (must be same length as text).
     * @return The decrypted string.
     */
    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char textChar = text.charAt(i);
            char keyChar = key.charAt(i);
            result.append((char) (textChar - keyChar));
        }
        return result.toString();
    }

    /**
     * Main method to demonstrate the encryption and decryption process.
     */
    public static void main(String[] args) {
        String key = "aaaaaaa";         // Key string
        String decrypted = "abcdefg";   // Original plaintext
        String encrypted = encrypt(decrypted, key);  // Encrypt the plaintext

        // Print the encrypted result
        System.out.println(encrypt(decrypted, key));

        // Print the decrypted result
        System.out.println(decrypt(encrypted, key));
    }
}