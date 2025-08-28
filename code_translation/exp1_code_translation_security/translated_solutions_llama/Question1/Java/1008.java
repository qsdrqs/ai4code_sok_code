public class Main {

    /**
     * Encrypts the given plaintext using the provided key.
     * 
     * @param key       The key to use for encryption.
     * @param plaintext The text to be encrypted.
     * @return The encrypted ciphertext.
     */
    public static String encrypt(String key, String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char keyC = key.charAt(i % key.length());
            char cipherC = (char) ((plaintext.charAt(i) + keyC) % 256);
            ciphertext.append(cipherC);
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts the given ciphertext using the provided key.
     * 
     * @param key        The key to use for decryption.
     * @param ciphertext The text to be decrypted.
     * @return The decrypted plaintext.
     */
    public static String decrypt(String key, String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char keyC = key.charAt(i % key.length());
            char plainC = (char) ((256 + ciphertext.charAt(i) - keyC) % 256);
            plaintext.append(plainC);
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        String key = "123";
        String plaintext = "helloworld";
        String ciphertext = encrypt(key, plaintext);
        System.out.println("Encrypted: " + ciphertext);
        System.out.println("Decrypted: " + decrypt(key, ciphertext));
    }
}