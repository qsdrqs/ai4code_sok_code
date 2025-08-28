public class XORCipher {

    /**
     * Encrypts a message using XOR encryption with a given key.
     *
     * @param message The message to encrypt.
     * @param key     The key used for encryption.
     * @return The encrypted message.
     */
    public static String encrypt(String message, String key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char messageChar = message.charAt(i);
            char keyChar = key.charAt(i % key.length());
            encrypted.append((char) (messageChar ^ keyChar));
        }
        return encrypted.toString();
    }

    /**
     * Decrypts an encrypted message using XOR encryption with the same key.
     * Since XOR is symmetric, decryption is the same as encryption.
     *
     * @param encrypted The encrypted message.
     * @param key       The key used for decryption.
     * @return The decrypted message.
     */
    public static String decrypt(String encrypted, String key) {
        return encrypt(encrypted, key);
    }

    /**
     * Main method to test the encryption and decryption.
     * Encrypts "Hello world" with the key "key", then decrypts it and prints the result.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println(decrypt(encrypt("Hello world", "key"), "key"));
    }
}