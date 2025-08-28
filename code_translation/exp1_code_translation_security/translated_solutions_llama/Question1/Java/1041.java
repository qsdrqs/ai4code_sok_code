public class Main {

    /**
     * Encrypts a given string by shifting each character by a specified key.
     * 
     * @param string The string to be encrypted.
     * @param key    The shift key for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String string, int key) {
        StringBuilder newString = new StringBuilder();
        for (char c : string.toCharArray()) {
            newString.append((char) (c + key));
        }
        return newString.toString();
    }

    /**
     * Decrypts a given string by shifting each character back by a specified key.
     * 
     * @param string The string to be decrypted.
     * @param key    The shift key for decryption.
     * @return The decrypted string.
     */
    public static String decrypt(String string, int key) {
        StringBuilder newString = new StringBuilder();
        for (char c : string.toCharArray()) {
            newString.append((char) (c - key));
        }
        return newString.toString();
    }

    public static void main(String[] args) {
        String msg = encrypt("this is cool if it really works", 2);
        String res = decrypt(msg, 2);
        System.out.println("Encrypted Message: " + msg);
        System.out.println("Decrypted Message: " + res);
    }
}