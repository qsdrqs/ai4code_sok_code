public class VigenereCipher {

    /**
     * Encrypts the given plaintext using the provided key.
     * 
     * @param plaintext The text to be encrypted.
     * @param key       The key used for encryption.
     * @return The encrypted ciphertext.
     */
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            char keyChar = key.charAt(i % key.length());
            // Convert characters to their corresponding alphabetical positions (a=0, b=1, ..., z=25)
            int plainPos = plainChar - 'a';
            int keyPos = keyChar - 'a';
            // Perform encryption
            int cipherPos = (plainPos + keyPos) % 26;
            // Convert back to character and append to ciphertext
            ciphertext.append((char) (cipherPos + 'a'));
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts the given ciphertext using the provided key.
     * 
     * @param ciphertext The text to be decrypted.
     * @param key        The key used for decryption.
     * @return The decrypted plaintext.
     */
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            char keyChar = key.charAt(i % key.length());
            // Convert characters to their corresponding alphabetical positions (a=0, b=1, ..., z=25)
            int cipherPos = cipherChar - 'a';
            int keyPos = keyChar - 'a';
            // Perform decryption
            int plainPos = (cipherPos - keyPos + 26) % 26;
            // Convert back to character and append to plaintext
            plaintext.append((char) (plainPos + 'a'));
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "helloworld";
        String key = "python";
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);
        String decrypted = decrypt(ciphertext, key);
        System.out.println(decrypted);
        assert plaintext.equals(decrypted);
    }
}