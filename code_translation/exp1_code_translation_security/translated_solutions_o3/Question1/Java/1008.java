public class Vigenere256 {

    /**
     * Encrypts the given plain-text with the provided key.
     * Each character is handled modulo 256, exactly like in the Python version.
     */
    public static String encrypt(String key, String plaintext) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char keyChar   = key.charAt(i % key.length());
            char plainChar = plaintext.charAt(i);

            int cipherCode = (plainChar + keyChar) % 256;  // modulo 256
            ciphertext.append((char) cipherCode);
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts the given cipher-text with the provided key.
     * Mirrors the encrypt method, again working modulo 256.
     */
    public static String decrypt(String key, String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char keyChar    = key.charAt(i % key.length());
            char cipherChar = ciphertext.charAt(i);

            int plainCode = (256 + cipherChar - keyChar) % 256;  // modulo 256
            plaintext.append((char) plainCode);
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        String key        = "123";
        String plaintext  = "helloworld";
        String ciphertext = encrypt(key, plaintext);

        // Equivalent to: print(decrypt('123', encrypt('123', 'helloworld')))
        System.out.println(decrypt(key, ciphertext));  // prints: helloworld
    }
}