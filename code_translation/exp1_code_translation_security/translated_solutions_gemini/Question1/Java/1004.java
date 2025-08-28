/**
 * A class to perform XOR encryption and decryption on strings.
 */
public class XorCipher {

    /**
     * Given a message and a key, encrypt the message using XOR encryption.
     *
     * @param message The plain text message to encrypt.
     * @param key     The encryption key. The key is repeated if it's shorter than the message.
     * @return The encrypted message as a String.
     */
    public static String encrypt(String message, String key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            // XOR the character from the message with the character from the key.
            // The modulo operator (%) ensures the key is repeated for the length of the message.
            char encryptedChar = (char) (message.charAt(i) ^ key.charAt(i % key.length()));
            encrypted.append(encryptedChar);
        }
        return encrypted.toString();
    }

    /**
     * Given an encrypted message and a key, decrypt the message using XOR encryption.
     * <p>
     * Note: XOR encryption is symmetrical, so decryption is the same operation as encryption.
     *
     * @param encrypted The encrypted message to decrypt.
     * @param key       The decryption key (must be the same as the encryption key).
     * @return The decrypted, original message as a String.
     */
    public static String decrypt(String encrypted, String key) {
        // Decryption is the same as encryption for a XOR cipher.
        return encrypt(encrypted, key);
    }

    public static void main(String[] args) {
        // The main method demonstrates the encrypt and decrypt functions.
        // It encrypts "Hello world" with the key "key", then decrypts the result
        // with the same key, and prints the final output to the console.
        System.out.println(decrypt(encrypt("Hello world", "key"), "key"));
    }
}