public final class Vigenere256 {

    private Vigenere256() {
        /* utility class – do not instantiate */
    }

    /**
     * Encrypts a plain text string with the given key.
     *
     * Behaviour is identical to the provided Python version:
     *     encrypted_c = chr((ord(text[i]) + ord(key[i % keyLen])) % 256)
     *
     * @param key    the (non-empty) key
     * @param text   the text to encrypt
     * @return the encrypted text
     */
    public static String encrypt(String key, String text) {
        StringBuilder out = new StringBuilder(text.length());

        int keyLen = key.length();
        for (int i = 0; i < text.length(); i++) {
            char keyChar = key.charAt(i % keyLen);
            char txtChar = text.charAt(i);

            char encChar = (char) ((txtChar + keyChar) & 0x00FF); // % 256
            out.append(encChar);
        }
        return out.toString();
    }

    /**
     * Decrypts text that was encrypted with {@link #encrypt(String, String)}.
     *
     *     decrypted_c = chr((ord(text[i]) - ord(key[i % keyLen]) + 256) % 256)
     *
     * @param key    the key that was used for encryption
     * @param text   the cipher text
     * @return the decrypted (original) text
     */
    public static String decrypt(String key, String text) {
        StringBuilder out = new StringBuilder(text.length());

        int keyLen = key.length();
        for (int i = 0; i < text.length(); i++) {
            char keyChar = key.charAt(i % keyLen);
            char cipherChar = text.charAt(i);

            char decChar = (char) ((cipherChar - keyChar + 256) & 0x00FF); // % 256
            out.append(decChar);
        }
        return out.toString();
    }

    /* -------------------------------------------------------------
     * Quick self-test
     * -------------------------------------------------------------
     */
    public static void main(String[] args) {
        String key  = "secret";
        String data = "Hello, world!";
        
        String enc = encrypt(key, data);
        String dec = decrypt(key, enc);

        System.out.println("Plain     : " + data);
        System.out.println("Encrypted : " + enc);
        System.out.println("Decrypted : " + dec);
    }
}