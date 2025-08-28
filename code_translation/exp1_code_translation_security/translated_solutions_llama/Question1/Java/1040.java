public class Main {

    /**
     * Encrypts a string with a given key using XOR operation.
     * 
     * @param string The string to be encrypted.
     * @param key    The key used for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String string, String key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            char k = key.charAt(i % key.length());
            encrypted.append((char) (c ^ k));
        }
        return encrypted.toString();
    }

    /**
     * Decrypts a string with a given key using XOR operation.
     * 
     * @param string The string to be decrypted.
     * @param key    The key used for decryption.
     * @return The decrypted string.
     */
    public static String decrypt(String string, String key) {
        return encrypt(string, key);
    }

    public static void main(String[] args) {
        String originalString = "sadasdasdsa";
        String key = "1234";
        String encryptedString = encrypt(originalString, key);
        String decryptedString = decrypt(encryptedString, key);

        System.out.println("Original String: " + originalString);
        System.out.println("Encrypted String: " + encryptedString);
        System.out.println("Decrypted String: " + decryptedString);

        // Assert statement equivalent in Java
        if (!decryptedString.equals(originalString)) {
            throw new AssertionError("Decrypted string does not match the original string");
        }
    }
}