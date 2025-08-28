public class Encryption {
    
    public static String encrypt(String msg, String key) {
        // System.out.println(msg);
        String temp = "";
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            // System.out.println(c);
            System.out.println(temp);
            temp = temp + c + key;
        }
        return temp;
    }
    
    public static String decrypt(String msg, String key) {
        int control = 0;
        String temp = "";
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            if (control % 2 == 0) {
                temp += c;
            }
            control = control + 1;
        }
        // System.out.println(temp);
        return temp;
    }
    
    public static void main(String[] args) {
        System.out.println(encrypt("a", "b"));
        System.out.println(decrypt("ab", "b"));
    }
}