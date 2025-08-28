public class XORCipher {

    /**
     * Encrypts a string using XOR with a repeating key.
     *
     * @param string The input string to encrypt.
     * @param key    The key used for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String string, String key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char sChar = string.charAt(i);
            char keyChar = key.charAt(i % key.length());
            encrypted.append((char) (sChar ^ keyChar));
        }
        return encrypted.toString();
    }

    /**
     * Decrypts a string using XOR with the same key used for encryption.
     * Since XOR is symmetric, decryption is the same as encryption.
     *
     * @param string The encrypted string to decrypt.
     * @param key    The key used for decryption.
     * @return The decrypted string.
     */
    public static String decrypt(String string, String key) {
        return encrypt(string, key);
    }

    /**
     * Main method to test the encryption and decryption.
     * This mirrors the assert statement from the original Python code.
     * Note: Java assertions are disabled by default. To enable them,
     * run the program with the -ea flag.
     */
    public static void main(String[] args) {
        assert decrypt(encrypt("sadasdasdsa", "1234"), "1234").equals("sadasdasdsa");
    }
}