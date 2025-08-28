/**
 * This class provides methods to encrypt and decrypt a string using a simple character offset cipher.
 */
public class SimpleCipher {

    /**
     * Encrypts a string by shifting each character by a given key value.
     *
     * @param text The string to encrypt.
     * @param key The integer value to shift characters by.
     * @return The encrypted string.
     */
    public static String encrypt(String text, int key) {
        // StringBuilder is used for efficient string concatenation in a loop.
        StringBuilder encrypted = new StringBuilder();
        
        // Iterate over each character in the input string.
        for (char c : text.toCharArray()) {
            // Shift the character by the key and append it to the result.
            encrypted.append((char) (c + key));
        }
        
        return encrypted.toString();
    }

    /**
     * Decrypts a string by shifting each character back by a given key value.
     *
     * @param text The string to decrypt.
     * @param key The integer value used for encryption.
     * @return The decrypted string.
     */
    public static String decrypt(String text, int key) {
        // StringBuilder is used for efficient string concatenation in a loop.
        StringBuilder decrypted = new StringBuilder();
        
        // Iterate over each character in the input string.
        for (char c : text.toCharArray()) {
            // Shift the character back by the key and append it to the result.
            decrypted.append((char) (c - key));
        }
        
        return decrypted.toString();
    }

    /**
     * The main method, which serves as the entry point for the program.
     * It demonstrates the encryption and decryption process.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String originalString = "Hello World!";
        int key = 5;

        // Encrypt the original string.
        String encrypted = encrypt(originalString, key);
        
        // Decrypt the encrypted string to get the original back.
        String decrypted = decrypt(encrypted, key);

        // Print the results to the console.
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}