/**
 * A class that provides symmetric encryption and decryption using XOR operation.
 */
public class SymmetricEncryption {

    /**
     * Encrypts a given string using a given symmetric key.
     * 
     * @param string The input string to be encrypted.
     * @param key    The symmetric key used for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String string, String key) {
        StringBuilder encryptedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char inputChar = string.charAt(i);
            char keyChar = key.charAt(i % key.length());
            encryptedString.append((char) (inputChar ^ keyChar));
        }
        return encryptedString.toString();
    }

    /**
     * Decrypts a given string using a given symmetric key.
     * 
     * @param string The input string to be decrypted.
     * @param key    The symmetric key used for decryption.
     * @return The decrypted string.
     */
    public static String decrypt(String string, String key) {
        StringBuilder decryptedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char inputChar = string.charAt(i);
            char keyChar = key.charAt(i % key.length());
            decryptedString.append((char) (inputChar ^ keyChar));
        }
        return decryptedString.toString();
    }
}