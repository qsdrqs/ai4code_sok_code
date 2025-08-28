public class Main {

    /**
     * Encrypts a string using a given key.
     *
     * @param text Text to encrypt.
     * @param key  Integer key (shift value).
     * @return Encrypted string.
     */
    public static String encrypt(String text, int key) {
        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char shifted = (char) (text.charAt(i) + key);
            encrypted.append(shifted);
        }

        return encrypted.toString();
    }

    /**
     * Decrypts a string using a given key.
     *
     * @param text Text to decrypt.
     * @param key  Integer key (shift value).
     * @return Decrypted string.
     */
    public static String decrypt(String text, int key) {
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char shifted = (char) (text.charAt(i) - key);
            decrypted.append(shifted);
        }

        return decrypted.toString();
    }

    public static void main(String[] args) {
        String original = "Hello World!";
        int key = 5;

        String encrypted = encrypt(original, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}