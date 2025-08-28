public class EncryptDecrypt {

    /**
     * Function to encrypt a given string
     */
    public static String encrypt(String text, int key) {
        char[] textArray = text.toCharArray();
        for (int i = 0; i < textArray.length; i++) {
            textArray[i] = (char) (textArray[i] + key);
        }
        return new String(textArray);
    }

    /**
     * Function to decrypt a given string
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
        System.out.println(decrypt("ՀԳԿԷ", 1234));
    }
}