public class VigenereCipher {

    /**
     * Encrypts the given plaintext with the supplied key
     * using the same arithmetic performed in the original Python code.
     */
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);              // current plaintext character
            char k = key.charAt(i % key.length());     // corresponding key character (cyclic)

            char c = (char) ((p - 'a' + k - 'a') % 26 + 'a'); // encryption formula
            ciphertext.append(c);
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts the given ciphertext with the supplied key
     * (inverse operation of encrypt).
     */
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);             // current cipher character
            char k = key.charAt(i % key.length());     // corresponding key character (cyclic)

            char p = (char) ((c - k + 26) % 26 + 'a'); // decryption formula
            plaintext.append(p);
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        String plaintext = "helloworld";
        String key       = "python";

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        String decrypted = decrypt(ciphertext, key);
        System.out.println(decrypted);

        // Simple assertion equivalent
        if (!plaintext.equals(decrypted)) {
            throw new AssertionError("Decryption failed");
        }
    }
}