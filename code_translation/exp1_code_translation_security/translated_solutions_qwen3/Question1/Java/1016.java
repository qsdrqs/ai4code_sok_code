public class SymmetricCipher {

    /**
     * Encrypts a given string using a given symmetric key.
     * 
     * @param string The input string to be encrypted.
     * @param key    The symmetric key used for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String string, String key) {
        StringBuilder encrypted = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < string.length(); i++) {
            char stringChar = string.charAt(i);
            char keyChar = key.charAt(i % keyLength);
            encrypted.append((char) (stringChar ^ keyChar));
        }

        return encrypted.toString();
    }

    /**
     * Decrypts a given string using a given symmetric key.
     * 
     * @param string The input string to be decrypted.
     * @param key    The symmetric key used for decryption.
     * @return The decrypted string.
     */
    public static String decrypt(String string, String key) {
        StringBuilder decrypted = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < string.length(); i++) {
            char stringChar = string.charAt(i);
            char keyChar = key.charAt(i % keyLength);
            decrypted.append((char) (stringChar ^ keyChar));
        }

        return decrypted.toString();
    }
}