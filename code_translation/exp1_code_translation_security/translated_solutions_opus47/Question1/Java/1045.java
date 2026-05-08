public class Main {
    /**
     * function to encrypt a given string
     */
    public static String encrypt(String text, int key) {
        char[] textList = text.toCharArray();
        for (int i = 0; i < textList.length; i++) {
            textList[i] = (char) (textList[i] + key);
        }
        return new String(textList);
    }

    /**
     * function to decrypt a given string
     */
    public static String decrypt(String text, int key) {
        char[] textList = text.toCharArray();
        for (int i = 0; i < textList.length; i++) {
            textList[i] = (char) (textList[i] - key);
        }
        return new String(textList);
    }

    public static void main(String[] args) {
        System.out.println(encrypt("name", 1234));
        System.out.println(decrypt("ՀԳԿԷ", 1234));
    }
}