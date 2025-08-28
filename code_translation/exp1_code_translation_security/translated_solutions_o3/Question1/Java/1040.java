public class XorCipher {

    /**
     * Encrypt a string with a given key.
     */
    public static String encrypt(String text, String key) {
        StringBuilder encrypted = new StringBuilder(text.length());

        for (int i = 0; i < text.length(); i++) {
            char plainChar  = text.charAt(i);
            char keyChar    = key.charAt(i % key.length());
            char cipherChar = (char) (plainChar ^ keyChar);   // XOR

            encrypted.append(cipherChar);
        }
        return encrypted.toString();
    }

    /**
     * Decrypt a string with a given key.
     * Because XOR is symmetric, encryption and decryption are the same.
     */
    public static String decrypt(String cipherText, String key) {
        return encrypt(cipherText, key);
    }

    // Simple usage / test
    public static void main(String[] args) {
        String plain = "sadasdasdsa";
        String key   = "1234";

        String cipher   = encrypt(plain, key);
        String restored = decrypt(cipher, key);

        assert plain.equals(restored) : "Decryption failed";

        System.out.println("Encrypted: " + cipher);
        System.out.println("Decrypted: " + restored);
    }
}