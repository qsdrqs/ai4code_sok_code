/**
 * A utility class for encrypting and decrypting strings using a simple
 * repeating key XOR cipher.
 */
public class XorCipher {

    /**
     * Encrypts a string with a given key.
     *
     * @param input The string to encrypt.
     * @param key   The encryption key. The key is repeated if it's shorter than the input string.
     * @return The encrypted string.
     */
    public static String encrypt(String input, String key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            // Perform XOR operation between the character from the input string
            // and the corresponding character from the key.
            char encryptedChar = (char) (input.charAt(i) ^ key.charAt(i % key.length()));
            encrypted.append(encryptedChar);
        }
        return encrypted.toString();
    }

    /**
     * Decrypts a string with a given key.
     *
     * @param input The string to decrypt.
     * @param key   The decryption key.
     * @return The decrypted string.
     */
    public static String decrypt(String input, String key) {
        // For a repeating key XOR cipher, encryption and decryption are the same operation.
        return encrypt(input, key);
    }

    /**
     * The main method serves as a test driver for the encryption and decryption logic.
     * It replicates the assertion test from the original Python code.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String originalString = "sadasdasdsa";
        String key = "1234";

        // Encrypt the original string
        String encryptedString = encrypt(originalString, key);
        
        // Decrypt the encrypted string
        String decryptedString = decrypt(encryptedString, key);

        System.out.println("Original:    " + originalString);
        System.out.println("Key:         " + key);
        System.out.println("Encrypted:   " + encryptedString);
        System.out.println("Decrypted:   " + decryptedString);
        
        // This is the Java equivalent of the Python assert statement.
        // It checks if the decrypted string is equal to the original.
        // In Java, we use the .equals() method to compare string content.
        assert decryptedString.equals(originalString);

        System.out.println("\nAssertion successful: The decrypted string matches the original.");
    }
}