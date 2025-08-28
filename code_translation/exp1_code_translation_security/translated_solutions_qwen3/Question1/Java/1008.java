public class EncryptDecrypt {

    /**
     * Encrypts the given plaintext using a repeating key.
     * Each character of the plaintext is combined with a corresponding character from the key.
     * The key is cycled if it is shorter than the plaintext.
     * The encryption is done by summing the ASCII values of the plaintext and key characters,
     * then taking modulo 256 to ensure the result is within the valid character range.
     *
     * @param key       The encryption key (string of characters)
     * @param plaintext The text to be encrypted
     * @return The encrypted text (ciphertext)
     */
    public static String encrypt(String key, String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char keyChar = key.charAt(i % key.length());
            char plainChar = plaintext.charAt(i);
            int sum = (int) plainChar + (int) keyChar;
            char cipherChar = (char) (sum % 256);
            ciphertext.append(cipherChar);
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts the given ciphertext using the same key used for encryption.
     * Each character of the ciphertext is combined with a corresponding character from the key.
     * The key is cycled if it is shorter than the ciphertext.
     * The decryption is done by subtracting the ASCII value of the key character from the ciphertext character,
     * then taking modulo 256 to ensure the result is within the valid character range.
     * Adding 256 before the modulo operation ensures the result is non-negative.
     *
     * @param key        The decryption key (string of characters)
     * @param ciphertext The text to be decrypted
     * @return The decrypted text (plaintext)
     */
    public static String decrypt(String key, String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char keyChar = key.charAt(i % key.length());
            char cipherChar = ciphertext.charAt(i);
            int diff = (int) cipherChar - (int) keyChar;
            int mod = (256 + diff) % 256;
            char plainChar = (char) mod;
            plaintext.append(plainChar);
        }
        return plaintext.toString();
    }

    /**
     * Main method to test the encryption and decryption functions.
     * Encrypts the string "helloworld" using the key "123",
     * then decrypts the result and prints the output.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        String key = "123";
        String original = "helloworld";
        String encrypted = encrypt(key, original);
        String decrypted = decrypt(key, encrypted);
        System.out.println(decrypted);  // Expected output: helloworld
    }
}