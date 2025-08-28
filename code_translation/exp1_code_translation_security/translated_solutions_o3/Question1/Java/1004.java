public class XOREncryption {

    /**
     * Encrypts (or decrypts) a message using simple XOR encryption.
     *
     * @param message The message to encrypt/decrypt
     * @param key     The key to use for XOR
     * @return The resulting encrypted/decrypted string
     */
    public static String encrypt(String message, String key) {
        StringBuilder result = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < message.length(); i++) {
            char mChar  = message.charAt(i);
            char kChar  = key.charAt(i % keyLength);
            char eChar  = (char) (mChar ^ kChar);  // XOR the two characters
            result.append(eChar);
        }
        return result.toString();
    }

    /**
     * Decrypts an XOR-encrypted string (XOR decrypting is identical
     * to encrypting with the same key).
     *
     * @param encrypted The encrypted string
     * @param key       The key used during encryption
     * @return The original plaintext string
     */
    public static String decrypt(String encrypted, String key) {
        return encrypt(encrypted, key);
    }

    public static void main(String[] args) {
        String key       = "key";
        String plaintext = "Hello world";

        // Encrypt
        String encrypted = encrypt(plaintext, key);

        // Decrypt and print
        System.out.println(decrypt(encrypted, key));  // -> Hello world
    }
}