public class VigenereCipher {

    /**
     * Encrypts plaintext with a key.
     *
     * @param key The encryption key.
     * @param plaintext The text to encrypt.
     * @return The encrypted ciphertext.
     */
    public static String encrypt(String key, String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char key_c = key.charAt(i % key.length());
            char plain_c = plaintext.charAt(i);
            
            // Add the character codes and take modulo 256 to get the new character code.
            char cipher_c = (char) ((plain_c + key_c) % 256);
            ciphertext.append(cipher_c);
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts ciphertext with a key.
     *
     * @param key The decryption key.
     * @param ciphertext The text to decrypt.
     * @return The decrypted plaintext.
     */
    public static String decrypt(String key, String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char key_c = key.charAt(i % key.length());
            char cipher_c = ciphertext.charAt(i);
            
            // To reverse the encryption, subtract the key's character code.
            // We add 256 before the modulo to ensure the result is positive.
            char plain_c = (char) ((256 + cipher_c - key_c) % 256);
            plaintext.append(plain_c);
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        // The main method demonstrates the usage of the encrypt and decrypt functions.
        String encrypted = encrypt("123", "helloworld");
        String decrypted = decrypt("123", encrypted);
        
        // This will print the original text: "helloworld"
        System.out.println(decrypted);
    }
}