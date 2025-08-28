public class Main {

    /**
     * Encrypts a message using XOR encryption with a given key.
     * 
     * @param message The message to be encrypted.
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
     * Decrypts an encrypted message using XOR decryption with a given key.
     * 
     * @param encrypted The encrypted message to be decrypted.
     * @param key       The key used for decryption.
     * @return The decrypted message.
     */
    public static String decrypt(String encrypted, String key) {
        return encrypt(encrypted, key);
    }

    public static void main(String[] args) {
        String originalMessage = "Hello world";
        String key = "key";
        String encryptedMessage = encrypt(originalMessage, key);
        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println(decryptedMessage);
    }
}