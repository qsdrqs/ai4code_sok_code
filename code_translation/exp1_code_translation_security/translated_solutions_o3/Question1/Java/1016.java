public final class SymmetricCipher {

    private SymmetricCipher() {
        /* Utility class – prevent instantiation */
    }

    /**
     * Encrypts a given string using a given symmetric key.
     *
     * @param input the plain-text string to encrypt
     * @param key   the symmetric key
     * @return the encrypted string
     */
    public static String encrypt(String input, String key) {
        StringBuilder encrypted = new StringBuilder(input.length());

        for (int i = 0; i < input.length(); i++) {
            char plainChar  = input.charAt(i);
            char keyChar    = key.charAt(i % key.length());
            char cipherChar = (char) (plainChar ^ keyChar);  // XOR operation
            encrypted.append(cipherChar);
        }

        return encrypted.toString();
    }

    /**
     * Decrypts a given string using a given symmetric key.
     * Because XOR is its own inverse, the logic is identical to encryption.
     *
     * @param input the encrypted string to decrypt
     * @param key   the symmetric key
     * @return the decrypted (plain-text) string
     */
    public static String decrypt(String input, String key) {
        // Same implementation as encrypt
        StringBuilder decrypted = new StringBuilder(input.length());

        for (int i = 0; i < input.length(); i++) {
            char cipherChar = input.charAt(i);
            char keyChar    = key.charAt(i % key.length());
            char plainChar  = (char) (cipherChar ^ keyChar);  // XOR operation
            decrypted.append(plainChar);
        }

        return decrypted.toString();
    }
}