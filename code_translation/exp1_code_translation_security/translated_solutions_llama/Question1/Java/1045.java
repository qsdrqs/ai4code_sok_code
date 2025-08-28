public class Main {

    /**
     * Function to encrypt a given string by shifting each character by a specified key.
     * 
     * @param text The string to be encrypted.
     * @param key  The shift key for encryption.
     * @return The encrypted string.
     */
    public static String encrypt(String text, int key) {
        char[] textArray = text.toCharArray();
        for (int i = 0; i < textArray.length; i++) {
            textArray[i] = (char) (textArray[i] + key);
        }
        return new String(textArray);
    }

    /**
     * Function to decrypt a given string by shifting each character back by a specified key.
     * 
     * @param text The string to be decrypted.
     * @param key  The shift key for decryption.
     * @return The decrypted string.
     */
    public static String decrypt(String text, int key) {
        char[] textArray = text.toCharArray();
        for (int i = 0; i < textArray.length; i++) {
            textArray[i] = (char) (textArray[i] - key);
        }
        return new String(textArray);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("name", 1234));
        System.out.println(decrypt("\u0490\u0413\u041a\u0417", 1234));
    }
}