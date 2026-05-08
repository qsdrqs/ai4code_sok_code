public class XORCipher {

    /**
     * Encrypts a given string using a given symmetric key.
     */
    public static String encrypt(String string, String key) {
        StringBuilder encryptedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            encryptedString.append((char) (string.charAt(i) ^ key.charAt(i % key.length())));
        }
        return encryptedString.toString();
    }

    /**
     * Decrypts a given string using a given symmetric key.
     */
    public static String decrypt(String string, String key) {
        StringBuilder decryptedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            decryptedString.append((char) (string.charAt(i) ^ key.charAt(i % key.length())));
        }
        return decryptedString.toString();
    }
}