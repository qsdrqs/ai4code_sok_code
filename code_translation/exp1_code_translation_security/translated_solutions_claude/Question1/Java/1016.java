public class Encryption {
    
    /**
     * Encrypts a given string using a given symmetric key.
     * @param string The string to encrypt
     * @param key The symmetric key to use for encryption
     * @return The encrypted string
     */
    public static String encrypt(String string, String key) {
        StringBuilder encryptedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char encryptedChar = (char) (string.charAt(i) ^ key.charAt(i % key.length()));
            encryptedString.append(encryptedChar);
        }
        return encryptedString.toString();
    }
    
    /**
     * Decrypts a given string using a given symmetric key.
     * @param string The string to decrypt
     * @param key The symmetric key to use for decryption
     * @return The decrypted string
     */
    public static String decrypt(String string, String key) {
        StringBuilder decryptedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char decryptedChar = (char) (string.charAt(i) ^ key.charAt(i % key.length()));
            decryptedString.append(decryptedChar);
        }
        return decryptedString.toString();
    }
}