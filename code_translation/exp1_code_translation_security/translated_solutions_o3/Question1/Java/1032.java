/**
 * Two simple helper methods to encrypt and decrypt a string
 * by shifting every character’s Unicode code-point with
 * the same symmetric key.
 */
public class SymmetricCipher {

    /**
     * Encrypts a string with the supplied key.
     *
     * @param text the plaintext to encrypt
     * @param key  the integer key used for shifting
     * @return the encrypted text
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
     * Decrypts a string with the supplied key.
     *
     * @param text the ciphertext to decrypt
     * @param key  the integer key used for shifting
     * @return the decrypted (original) text
     */
    public static String decrypt(String text, int key) {
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char shifted = (char) (text.charAt(i) - key);
            decrypted.append(shifted);
        }

        return decrypted.toString();
    }

    /**
     * Program entry point. Demonstrates encryption and decryption.
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