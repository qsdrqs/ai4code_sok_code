public class Main {

    /**
     * Encrypts a given string using a given symmetric key.
     * 
     * @param string The string to be encrypted.
     * @param key    The symmetric key used for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String string, String key) {
        StringBuilder encryptedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            char k = key.charAt(i % key.length());
            encryptedString.append((char) (c ^ k));
        }
        return encryptedString.toString();
    }

    /**
     * Decrypts a given string using a given symmetric key.
     * 
     * @param string The string to be decrypted.
     * @param key    The symmetric key used for decryption.
     * @return The decrypted string.
     */
    public static String decrypt(String string, String key) {
        // Note: The XOR operation is symmetric, so the same function can be used for both encryption and decryption.
        return encrypt(string, key);
    }

    public static void main(String[] args) {
        String originalString = "Hello, World!";
        String symmetricKey = "secretkey";

        String encryptedString = encrypt(originalString, symmetricKey);
        System.out.println("Encrypted String: " + encryptedString);

        String decryptedString = decrypt(encryptedString, symmetricKey);
        System.out.println("Decrypted String: " + decryptedString);
    }
}