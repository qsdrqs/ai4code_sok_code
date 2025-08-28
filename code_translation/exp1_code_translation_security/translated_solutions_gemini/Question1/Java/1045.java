/**
 * A utility class for simple text encryption and decryption.
 */
public class TextEncryptor {

    /**
     * Function to encrypt a given string by shifting its character codes.
     *
     * @param text The plain text string to encrypt.
     * @param key  The integer value to shift the characters by.
     * @return The encrypted string.
     */
    public static String encrypt(String text, int key) {
        // StringBuilder is an efficient way to build strings in Java.
        StringBuilder encryptedText = new StringBuilder();

        // Iterate over each character of the input string.
        for (char character : text.toCharArray()) {
            // Get the integer (Unicode) value of the character, add the key,
            // and cast it back to a character.
            char encryptedChar = (char) (character + key);
            // Append the newly encrypted character to our result.
            encryptedText.append(encryptedChar);
        }

        return encryptedText.toString();
    }

    /**
     * Function to decrypt a given string by reversing the character code shift.
     *
     * @param text The encrypted text string to decrypt.
     * @param key  The integer value that was used for encryption.
     * @return The decrypted string.
     */
    public static String decrypt(String text, int key) {
        // StringBuilder is an efficient way to build strings in Java.
        StringBuilder decryptedText = new StringBuilder();

        // Iterate over each character of the input string.
        for (char character : text.toCharArray()) {
            // Get the integer (Unicode) value of the character, subtract the key,
            // and cast it back to a character.
            char decryptedChar = (char) (character - key);
            // Append the newly decrypted character to our result.
            decryptedText.append(decryptedChar);
        }

        return decryptedText.toString();
    }

    /**
     * The main method to run the demonstration.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Call the encrypt method and print the result.
        System.out.println(encrypt("name", 1234));

        // Call the decrypt method and print the result.
        System.out.println(decrypt("ՀԳԿԷ", 1234));
    }
}