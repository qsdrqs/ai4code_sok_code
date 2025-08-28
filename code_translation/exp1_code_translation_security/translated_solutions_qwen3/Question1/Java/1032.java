public class SymmetricEncryption {

    /**
     * Encrypts a given string using a symmetric key by shifting each character's Unicode value.
     *
     * @param input The string to be encrypted.
     * @param key   The symmetric key used for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            encrypted.append((char) (c + key));
        }
        return encrypted.toString();
    }

    /**
     * Decrypts a given string using a symmetric key by reversing the Unicode shift.
     *
     * @param input The string to be decrypted.
     * @param key   The symmetric key used for decryption.
     * @return The decrypted string.
     */
    public static String decrypt(String input, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            decrypted.append((char) (c - key));
        }
        return decrypted.toString();
    }

    /**
     * Main method to test the encryption and decryption functions.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        String original = "Hello World!";
        int key = 5;

        String encrypted = encrypt(original, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}