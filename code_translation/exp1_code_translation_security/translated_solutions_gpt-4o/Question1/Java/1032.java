public class SymmetricEncryption {

    /**
     * Encrypts a string using a given key.
     */
    public static String encrypt(String string, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            encrypted.append((char) (string.charAt(i) + key));
        }
        return encrypted.toString();
    }

    /**
     * Decrypts a string using a given key.
     */
    public static String decrypt(String string, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            decrypted.append((char) (string.charAt(i) - key));
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        String string = "Hello World!";
        int key = 5;
        String encrypted = encrypt(string, key);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}