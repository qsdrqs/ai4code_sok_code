/**
 * A simple utility class for XOR encryption and decryption.
 * This is a symmetric cipher, meaning the same key is used for both
 * encryption and decryption.
 */
public class XorCipher {

    /**
     * Processes a string with a key using the XOR operation.
     * This method is used by both encrypt and decrypt since XOR is its own inverse.
     *
     * @param input The string to process (plaintext or ciphertext).
     * @param key   The symmetric key.
     * @return The processed string.
     */
    private static String process(String input, String key) {
        // Using StringBuilder is more efficient for string concatenation in a loop.
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            // Get the character from the input string.
            char inputChar = input.charAt(i);
            
            // Get the corresponding character from the key, repeating the key if necessary.
            char keyChar = key.charAt(i % key.length());

            // Perform the XOR operation and cast the result back to a char.
            char processedChar = (char) (inputChar ^ keyChar);
            
            // Append the resulting character to our output.
            output.append(processedChar);
        }

        return output.toString();
    }

    /**
     * Encrypts a given string using a given symmetric key.
     *
     * @param message The string to encrypt.
     * @param key     The symmetric key.
     * @return The encrypted string.
     */
    public static String encrypt(String message, String key) {
        return process(message, key);
    }

    /**
     * Decrypts a given string using a given symmetric key.
     *
     * @param encryptedMessage The string to decrypt.
     * @param key              The symmetric key.
     * @return The decrypted string.
     */
    public static String decrypt(String encryptedMessage, String key) {
        // The XOR operation is its own inverse, so decryption is the same as encryption.
        return process(encryptedMessage, key);
    }

    /**
     * Main method to demonstrate the encryption and decryption process.
     */
    public static void main(String[] args) {
        String originalMessage = "Hello, World! This is a test of the XOR cipher.";
        String secretKey = "SuperSecretKey123";

        System.out.println("Original Message:  " + originalMessage);
        System.out.println("Secret Key:        " + secretKey);
        System.out.println("----------------------------------------------------");

        // Encrypt the message
        String encryptedMessage = encrypt(originalMessage, secretKey);
        System.out.println("Encrypted Message: " + encryptedMessage);

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, secretKey);
        System.out.println("Decrypted Message: " + decryptedMessage);
        
        // Verify that the decrypted message matches the original
        System.out.println("----------------------------------------------------");
        System.out.println("Verification Successful: " + originalMessage.equals(decryptedMessage));
    }
}