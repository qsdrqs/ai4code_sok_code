public class VigenereCipher {

    /**
     * Encrypts the given plaintext using a Vigenère cipher with the provided key.
     * 
     * @param plaintext The input string to be encrypted (assumed to be lowercase letters).
     * @param key       The key used for encryption (assumed to be lowercase letters).
     * @return The encrypted string.
     */
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            char k = key.charAt(i % key.length());

            // Calculate the encrypted character using the Vigenère cipher formula
            int val = ((int) p + (int) k - 2 * (int) 'a') % 26 + (int) 'a';
            ciphertext.append((char) val);
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts the given ciphertext using a Vigenère cipher with the provided key.
     * 
     * @param ciphertext The encrypted string to be decrypted (assumed to be lowercase letters).
     * @param key        The key used for decryption (assumed to be lowercase letters).
     * @return The decrypted string.
     */
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            char k = key.charAt(i % key.length());

            // Calculate the decrypted character using the Vigenère cipher formula
            int val = ((int) c - (int) k + 26) % 26 + (int) 'a';
            plaintext.append((char) val);
        }
        return plaintext.toString();
    }

    /**
     * Main method to test the encryption and decryption functions.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        String plaintext = "helloworld";
        String key = "python";

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        // Decrypt the ciphertext
        String decrypted = decrypt(ciphertext, key);
        System.out.println("Decrypted: " + decrypted);

        // Assert that the decrypted text matches the original plaintext
        assert plaintext.equals(decrypted) : "Decryption failed!";
    }
}