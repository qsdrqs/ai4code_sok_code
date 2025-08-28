public class CaesarCipher {

    /**
     * Encrypts a string by shifting each character by a given key.
     *
     * @param str The string to encrypt.
     * @param key The integer value to shift each character by.
     * @return The encrypted string.
     */
    public static String encrypt(String str, int key) {
        // In Java, it's more efficient to build strings using StringBuilder.
        StringBuilder newString = new StringBuilder();

        // We can iterate over the characters of the string.
        for (char c : str.toCharArray()) {
            // A char can be treated as a number; we add the key and cast it back to a char.
            char encryptedChar = (char) (c + key);
            newString.append(encryptedChar);
        }
        return newString.toString();
    }

    /**
     * Decrypts a string by shifting each character back by a given key.
     *
     * @param str The string to decrypt.
     * @param key The integer value to shift each character back by.
     * @return The decrypted string.
     */
    public static String decrypt(String str, int key) {
        StringBuilder newString = new StringBuilder();

        for (char c : str.toCharArray()) {
            // To decrypt, we subtract the key and cast the result back to a char.
            char decryptedChar = (char) (c - key);
            newString.append(decryptedChar);
        }
        return newString.toString();
    }

    /**
     * The main method, which serves as the entry point for the program.
     */
    public static void main(String[] args) {
        // The equivalent of the Python script's main execution block.
        String msg = encrypt("this is cool if it really works", 2);
        String res = decrypt(msg, 2);

        // Use System.out.println() to print to the console.
        System.out.println(msg);
        System.out.println(res);
    }
}