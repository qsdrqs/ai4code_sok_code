/**
 * This class contains the Java translation of the provided Python encryption and decryption functions.
 */
public class SimpleEncryptor {

    /**
     * Encrypts a message by interleaving each character with a key.
     * This is the Java equivalent of the Python 'encrypt' function.
     *
     * @param msg The message string to encrypt.
     * @param key The key string to use for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String msg, String key) {
        // Using StringBuilder is more efficient for string concatenation in a loop in Java.
        StringBuilder temp = new StringBuilder();
        
        // Loop through each character of the message string.
        for (char c : msg.toCharArray()) {
            // This line replicates the 'print(temp)' from the Python loop.
            System.out.println(temp.toString());
            
            // Append the character and the key to the result.
            temp.append(c).append(key);
        }
        return temp.toString();
    }

    /**
     * Decrypts a message by taking every other character, starting from the first one.
     * This is the Java equivalent of the Python 'decrypt' function.
     *
     * @param msg The encrypted message string.
     * @param key The key (unused in this decryption logic, but included for signature consistency).
     * @return The decrypted string.
     */
    public static String decrypt(String msg, String key) {
        StringBuilder temp = new StringBuilder();
        
        // We can use a standard for-loop with an index to achieve the same result
        // as the 'control' variable in the Python version.
        for (int i = 0; i < msg.length(); i++) {
            // Check if the index is even (0, 2, 4, ...).
            if (i % 2 == 0) {
                // Append the character at the even index to the result.
                temp.append(msg.charAt(i));
            }
        }
        return temp.toString();
    }

    /**
     * The main method to run the example, equivalent to the Python script's global scope execution.
     */
    public static void main(String[] args) {
        // The output of these lines will be identical to the Python script.
        System.out.println(encrypt("a", "b"));
        System.out.println(decrypt("ab", "b"));
    }
}