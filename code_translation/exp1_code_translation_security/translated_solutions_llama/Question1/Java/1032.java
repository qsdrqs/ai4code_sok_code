public class Main {

    /**
     * Encrypts a string using a given key.
     * 
     * @param string The string to be encrypted.
     * @param key    The symmetric key for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String string, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : string.toCharArray()) {
            encrypted.append((char) (c + key));
        }
        return encrypted.toString();
    }

    /**
     * Decrypts a string using a given key.
     * 
     * @param string The string to be decrypted.
     * @param key    The symmetric key for decryption.
     * @return The decrypted string.
     */
    public static String decrypt(String string, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : string.toCharArray()) {
            decrypted.append((char) (c - key));
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        /**
         * Main function.
         */
        String string = "Hello World!";
        int key = 5;
        String encrypted = encrypt(string, key);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}