public class Encryption {

    /**
     * Encrypts a given string by shifting each character's Unicode value by the key.
     *
     * @param text The string to be encrypted.
     * @param key  The integer value to shift each character by.
     * @return     The encrypted string.
     */
    public static String encrypt(String text, int key) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] + key);
        }
        return new String(chars);
    }

    /**
     * Decrypts a given string by shifting each character's Unicode value back by the key.
     *
     * @param text The string to be decrypted.
     * @param key  The integer value to shift each character by.
     * @return     The decrypted string.
     */
    public static String decrypt(String text, int key) {
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] - key);
        }
        return new String(chars);
    }

    /**
     * Main method to demonstrate the encryption and decryption functionality.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println(encrypt("name", 1234)); // Should print encrypted string
        System.out.println(decrypt("ՀԳԿԷ", 1234)); // Should print "name"
    }
}