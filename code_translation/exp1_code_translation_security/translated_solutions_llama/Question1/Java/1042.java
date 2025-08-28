public class Main {

    /**
     * Encrypts a given string using a Caesar cipher with a provided key.
     * 
     * @param key    The shift key for the Caesar cipher.
     * @param string The string to be encrypted.
     * @return The encrypted string.
     */
    public static String encrypt(int key, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        if (key < 0) {
            return null;
        }
        key = key % 26;
        StringBuilder newString = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                newString.append((char) ((c - base + key) % 26 + base));
            } else {
                newString.append(c);
            }
        }
        return newString.toString();
    }

    /**
     * Decrypts a given string using a Caesar cipher with a provided key.
     * 
     * @param key       The shift key for the Caesar cipher.
     * @param ciphertext The string to be decrypted.
     * @return The decrypted string.
     */
    public static String decrypt(String ciphertext, int key) {
        if (ciphertext == null || ciphertext.isEmpty()) {
            return null;
        }
        if (key < 0) {
            return null;
        }
        key = 26 - (key % 26);
        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                plaintext.append((char) ((c - base + key) % 26 + base));
            } else {
                plaintext.append(c);
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt(3, "Hello, World!"));
        System.out.println(decrypt(encrypt(3, "Hello, World!"), 3));
    }
}