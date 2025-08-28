public class SimpleEncryptDecrypt {

    public static String encrypt(String string, int key) {
        StringBuilder newString = new StringBuilder();
        for (char ch : string.toCharArray()) {
            newString.append((char) (ch + key));
        }
        return newString.toString();
    }

    public static String decrypt(String string, int key) {
        StringBuilder newString = new StringBuilder();
        for (char ch : string.toCharArray()) {
            newString.append((char) (ch - key));
        }
        return newString.toString();
    }

    public static void main(String[] args) {
        String msg = encrypt("this is cool if it really works", 2);
        String res = decrypt(msg, 2);
        System.out.println(msg);
        System.out.println(res);
    }
}