public class SymmetricEncryption {

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

    public static void main(String[] args) {
        String message = "Hello, World!";
        String key = "secret";

        String encrypted = encrypt(message, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }
}