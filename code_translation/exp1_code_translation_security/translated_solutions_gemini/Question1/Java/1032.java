/**
 * A Java class to demonstrate simple symmetric encryption and decryption.
 * This is a translation of the provided Python code.
 */
public class SymmetricEncryption {

    /**
     * Encrypts a string using a given key by shifting character codes.
     * This is a simple Caesar cipher-like substitution.
     *
     * @param str The string to encrypt.
     * @param key The integer key for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String str, int key) {
        // Use StringBuilder for efficient string concatenation in a loop
        StringBuilder encrypted = new StringBuilder();
        
        // Iterate over each character in the input string
        for (char c : str.toCharArray()) {
            // Shift the character's Unicode value by the key and append it
            encrypted.append((char) (c + key));
        }
        
        return encrypted.toString();
    }

    /**
     * Decrypts a string using a given key by shifting character codes back.
     *
     * @param str The string to decrypt.
     * @param key The integer key for decryption.
     * @return The decrypted string.
     */
    public static String decrypt(String str, int key) {
        // Use StringBuilder for efficient string concatenation in a loop
        StringBuilder decrypted = new StringBuilder();
        
        // Iterate over each character in the input string
        for (char c : str.toCharArray()) {
            // Shift the character's Unicode value back by the key and append it
            decrypted.append((char) (c - key));
        }
        
        return decrypted.toString();
    }

    /**
     * The main method to run the encryption and decryption demonstration.
     * This is the entry point of the Java application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String originalString = "Hello World!";
        int key = 5;

        // Encrypt the original string
        String encryptedString = encrypt(originalString, key);
        
        // Decrypt the encrypted string to get the original back
        String decryptedString = decrypt(encryptedString, key);

        // Print the results to the console
        System.out.println("Encrypted: " + encryptedString);
        System.out.println("Decrypted: " + decryptedString);
    }
}