/**
 * Simple XOR‐based symmetric cipher that can both encrypt and decrypt text.
 * (All libraries used below are part of the standard JDK.)
 */
public final class SymmetricCipher {

    private SymmetricCipher() {
        /* Utility class – prevent instantiation */
    }

    /**
     * Encrypt a piece of text with the supplied key.
     *
     * @param text the data to encrypt
     * @param key  the symmetric key
     * @return the encrypted string
     */
    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder(text.length());
        int keyLen = key.length();

        for (int i = 0; i < text.length(); i++) {
            char plainChar   = text.charAt(i);
            char keyChar     = key.charAt(i % keyLen);
            char cipherChar  = (char) (plainChar ^ keyChar); // XOR operation
            result.append(cipherChar);
        }
        return result.toString();
    }

    /**
     * Decrypt a piece of text with the supplied key.
     * (Because XOR is its own inverse, this is identical to `encrypt`.)
     *
     * @param cipherText the encrypted text
     * @param key        the symmetric key used during encryption
     * @return the decrypted/original string
     */
    public static String decrypt(String cipherText, String key) {
        StringBuilder result = new StringBuilder(cipherText.length());
        int keyLen = key.length();

        for (int i = 0; i < cipherText.length(); i++) {
            char cipherChar = cipherText.charAt(i);
            char keyChar    = key.charAt(i % keyLen);
            char plainChar  = (char) (cipherChar ^ keyChar); // XOR again
            result.append(plainChar);
        }
        return result.toString();
    }

    /* --- Optional demo --- */
    public static void main(String[] args) {
        String key   = "mySecretKey";
        String plain = "Hello, Java!";

        String encrypted = encrypt(plain, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("Plain     : " + plain);
        System.out.println("Encrypted : " + encrypted);
        System.out.println("Decrypted : " + decrypted);
    }
}