public class EncryptDecrypt {

    public static String encrypt(String msg, String key) {
        String temp = "";
        for (char c : msg.toCharArray()) {
            System.out.println(temp);
            temp = temp + c + key;
        }
        return temp;
    }

    public static String decrypt(String msg, String key) {
        int control = 0;
        String temp = "";
        for (char c : msg.toCharArray()) {
            if (control % 2 == 0) {
                temp += c;
            }
            control++;
        }
        return temp;
    }

    public static void main(String[] args) {
        System.out.println(encrypt("a", "b"));
        System.out.println(decrypt("ab", "b"));
    }
}