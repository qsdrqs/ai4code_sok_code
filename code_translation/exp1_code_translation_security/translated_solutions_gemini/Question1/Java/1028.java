/**
 * A class that provides methods to encrypt and decrypt a given string using a 
 * symmetric key (repeating key XOR cipher).
 * All dependencies are included in this file.
 */
public class SymmetricCipher {

    /**
     * Encrypts a given string using a given symmetric key.
     * This method uses a repeating key XOR cipher.
     *
     * @param text The string to be encrypted.
     * @param key The symmetric key to use for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String text, String key) {
        // Use StringBuilder for efficient string concatenation in a loop.
        StringBuilder encryptedString = new StringBuilder();

        // Iterate over each character of the input string.
        for (int i = 0; i < text.length(); i++) {
            // Perform a bitwise XOR operation between the character of the string
            // and the character of the key. The key is repeated if it's shorter
            // than the string by using the modulo operator.
            char encryptedChar = (char) (text.charAt(i) ^ key.charAt(i % key.length()));
            encryptedString.append(encryptedChar);
        }

        // Convert the StringBuilder to a String and return it.
        return encryptedString.toString();
    }

    /**
     * Decrypts a given string using a given symmetric key.
     * The decryption logic for a simple XOR cipher is identical to the encryption logic.
     *
     * @param encryptedText The string to be decrypted.
     * @param key The symmetric key that was used for encryption.
     * @return The original, decrypted string.
     */
    public static String decrypt(String encryptedText, String key) {
        // Because (A XOR B) XOR B = A, the encryption and decryption operations are the same.
        // We can simply call the encrypt method to decrypt the text.
        return encrypt(encryptedText, key);
    }

    /**
     * Main method to demonstrate the encryption and decryption functionality.
     */
    public static void main(String[] args) {
        String originalText = "Hello, Java! This is a secret message.";
        String secretKey = "mySuperSecretKey123";

        System.out.println("Original Text: " + originalText);
        System.out.println("Secret Key:    " + secretKey);
        System.out.println("-----------------------------------------");

        // Encrypt the text
        String encryptedText = encrypt(originalText, secretKey);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the text
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Decrypted Text: " + decryptedText);

        // Verify that the decrypted text matches the original text
        System.out.println("-----------------------------------------");
        System.out.println("Decryption successful: " + originalText.equals(decryptedText));
    }
}