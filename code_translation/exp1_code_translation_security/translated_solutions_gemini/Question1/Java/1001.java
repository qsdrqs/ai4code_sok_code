public class VigenereCipher {

    /**
     * Encrypts a string using a key with a Vigenère-like cipher.
     *
     * @param key    The encryption key.
     * @param text   The plaintext string to encrypt.
     * @return The encrypted ciphertext string.
     */
    public static String encrypt(String key, String text) {
        // StringBuilder is more efficient for concatenating strings in a loop
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            // Get the current character from the key, repeating the key if necessary
            char keyChar = key.charAt(i % key.length());
            
            // The encryption formula: (text_char + key_char) % 256
            // In Java, chars are treated as numbers in arithmetic operations.
            char encryptedChar = (char) ((text.charAt(i) + keyChar) % 256);
            
            encryptedText.append(encryptedChar);
        }
        
        return encryptedText.toString();
    }

    /**
     * Decrypts a string using a key with a Vigenère-like cipher.
     *
     * @param key    The decryption key.
     * @param text   The ciphertext string to decrypt.
     * @return The decrypted plaintext string.
     */
    public static String decrypt(String key, String text) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            // Get the current character from the key, repeating the key if necessary
            char keyChar = key.charAt(i % key.length());
            
            // The decryption formula: (text_char - key_char + 256) % 256
            // We add 256 to handle potential negative results from the subtraction,
            // ensuring the modulo operation works correctly for "wrap-around".
            char decryptedChar = (char) ((text.charAt(i) - keyChar + 256) % 256);
            
            decryptedText.append(decryptedChar);
        }
        
        return decryptedText.toString();
    }

    // Main method to demonstrate the functionality
    public static void main(String[] args) {
        String key = "SECRETKEY";
        String originalText = "This is a test message for the Vigenère-like cipher!";

        System.out.println("Original Text: " + originalText);
        System.out.println("Key:           " + key);
        System.out.println("----------------------------------------------------");

        // Encrypt the text
        String encryptedText = VigenereCipher.encrypt(key, originalText);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the text
        String decryptedText = VigenereCipher.decrypt(key, encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);
        
        // Verify correctness
        System.out.println("----------------------------------------------------");
        System.out.println("Decryption successful: " + originalText.equals(decryptedText));
    }
}