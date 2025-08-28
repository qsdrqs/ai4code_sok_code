public class Main {

    /**
     * Encrypts a given string using a provided key.
     * 
     * @param key    The encryption key.
     * @param string The string to be encrypted.
     * @return The encrypted string.
     */
    public static String encrypt(String key, String string) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char keyC = key.charAt(i % key.length());
            char encryptedC = (char) ((string.charAt(i) + keyC) % 256);
            encryptedText.append(encryptedC);
        }
        return encryptedText.toString();
    }

    /**
     * Decrypts a given string using a provided key.
     * 
     * @param key    The decryption key.
     * @param string The string to be decrypted.
     * @return The decrypted string.
     */
    public static String decrypt(String key, String string) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char keyC = key.charAt(i % key.length());
            char decryptedC = (char) ((string.charAt(i) - keyC + 256) % 256);
            decryptedText.append(decryptedC);
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        String key = "secret";
        String originalString = "Hello, World!";
        
        String encryptedString = encrypt(key, originalString);
        System.out.println("Encrypted String: " + encryptedString);
        
        String decryptedString = decrypt(key, encryptedString);
        System.out.println("Decrypted String: " + decryptedString);
    }
}