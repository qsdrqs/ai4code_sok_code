/**
 * This is a direct translation of the provided Python script.
 * The original Python comments have been preserved and JavaDoc comments have been added for clarity.
 */
public class PseudoCipher {

    // this is gonna be really scuffed but it might work
    // right now text and key must be equal length, but that can be changed to loop
    // to the front of the key again for very long text
    // it's TECHNICALLY a psuedo-cypher

    /**
     * Encrypts a text string using a key string by adding their character codes.
     *
     * @param text The plain text to encrypt.
     * @param key The key to use for encryption. Must be the same length as the text.
     * @return The encrypted string.
     */
    public static String encrypt(String text, String key) {
        // Using StringBuilder is more efficient for concatenating strings in a loop in Java.
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            // In Java, a 'char' can be treated as its integer (Unicode) value.
            // We add the values and then cast the resulting integer back to a char.
            char thisChar = (char) (text.charAt(i) + key.charAt(i));
            result.append(thisChar);
        }
        return result.toString();
    }

    /**
     * Decrypts a text string using a key string by subtracting their character codes.
     *
     * @param text The encrypted text to decrypt.
     * @param key The key that was used for encryption. Must be the same length as the text.
     * @return The decrypted string.
     */
    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            // We subtract the key's character value from the text's character value
            // and cast the result back to a char.
            char thisChar = (char) (text.charAt(i) - key.charAt(i));
            result.append(thisChar);
        }
        return result.toString();
    }

    /**
     * The main method to run the encryption and decryption demonstration.
     */
    public static void main(String[] args) {
        String key = "aaaaaaa";
        String decrypted = "abcdefg";

        // Encrypt the original text
        String encrypted = encrypt(decrypted, key);

        // The Python script prints the result of encrypting again, so we do the same.
        System.out.println(encrypt(decrypted, key));

        // Decrypt the encrypted text and print the original result
        System.out.println(decrypt(encrypted, key));
    }
}