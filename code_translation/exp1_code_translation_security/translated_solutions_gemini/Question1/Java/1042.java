/**
 * This class contains Java translations of the provided Python functions.
 */
public class Solution {

    /**
     * Encrypts a string using a Caesar cipher.
     *
     * @param key    The integer key for the shift. Must be non-negative.
     * @param string The string to encrypt. Must not be empty.
     * @return The encrypted string, or null if the input is invalid.
     */
    public static String encrypt(int key, String string) {
        // In Java, type checking is handled by the method signature.
        // A non-integer key or non-String string would cause a compile-time error.

        if (key < 0) {
            return null;
        }
        if (string == null || string.isEmpty()) {
            return null;
        }

        // Normalize the key to be within the 0-25 range
        if (key > 26) {
            key = key % 26;
        }

        StringBuilder newString = new StringBuilder();

        for (char i : string.toCharArray()) {
            if (Character.isLetter(i)) {
                if (Character.isLowerCase(i)) {
                    // Base for lowercase is 'a'
                    char shiftedChar = (char) (((i - 'a' + key) % 26) + 'a');
                    newString.append(shiftedChar);
                } else { // Uppercase
                    // Base for uppercase is 'A'
                    char shiftedChar = (char) (((i - 'A' + key) % 26) + 'A');
                    newString.append(shiftedChar);
                }
            } else {
                // Append non-alphabetic characters unchanged
                newString.append(i);
            }
        }
        return newString.toString();
    }

    /**
     * Function that decrypts a given string using a symmetric key (Vigenère-like cipher).
     *
     * @param ciphertext The string to be decrypted.
     * @param key        The symmetric key string.
     * @return The decrypted plaintext, or null for invalid input.
     */
    public static String decrypt(String ciphertext, String key) {
        // In Java, isinstance checks are handled by the method signature.
        if (ciphertext == null || key == null || ciphertext.isEmpty() || key.isEmpty()) {
            return null;
        }

        // This is a specific condition from the original Python code.
        if (key.length() == 1) {
            return ciphertext;
        }

        if (key.length() > ciphertext.length()) {
            return null;
        }

        int keyLen = key.length();
        int ciphertextLen = ciphertext.length();
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertextLen; i++) {
            // Get the integer (ASCII/Unicode) values of the characters
            int ciphertextVal = ciphertext.charAt(i);
            int keyVal = key.charAt(i % keyLen);

            // The formula from Python is (ciphertext_ascii - key_ascii) % 26.
            // We must handle the modulo of negative numbers to match Python's behavior.
            // In Java, -1 % 26 is -1. In Python, it's 25.
            // The formula (a % n + n) % n correctly handles this.
            int value = (ciphertextVal - keyVal);
            int decryptedVal = (value % 26 + 26) % 26;

            // The Python code adds 65 ('A') to the result, implying an uppercase result.
            plaintext.append((char) (decryptedVal + 'A'));
        }
        return plaintext.toString();
    }
}