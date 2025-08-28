public class EncryptionUtil {

    /**
     * Encrypts the input string using a repeating key.
     * Each character of the string is combined with the corresponding character of the key
     * using addition modulo 256.
     *
     * @param key    The encryption key (non-empty string)
     * @param string The input string to encrypt
     * @return The encrypted string
     */
    public static String encrypt(String key, String string) {
        char[] encrypted = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            char keyChar = key.charAt(i % key.length());
            int encryptedValue = (string.charAt(i) + keyChar) % 256;
            encrypted[i] = (char) encryptedValue;
        }
        return new String(encrypted);
    }

    /**
     * Decrypts the encrypted string using the same key used for encryption.
     * Each character of the encrypted string is combined with the corresponding character of the key
     * using subtraction modulo 256, ensuring the result is non-negative.
     *
     * @param key    The decryption key (non-empty string)
     * @param string The encrypted string to decrypt
     * @return The decrypted string
     */
    public static String decrypt(String key, String string) {
        char[] decrypted = new char[string.length()];
        for (int i = 0; i < string.length(); i++) {
            char keyChar = key.charAt(i % key.length());
            int decryptedValue = (string.charAt(i) - keyChar + 256) % 256;
            decrypted[i] = (char) decryptedValue;
        }
        return new String(decrypted);
    }

    // Optional: Main method for testing
    public static void main(String[] args) {
        String key = "secret";
        String original = "Hello, World!";
        String encrypted = encrypt(key, original);
        String decrypted = decrypt(key, encrypted);

        System.out.println("Original: " + original);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}