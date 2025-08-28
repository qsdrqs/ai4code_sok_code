public class CaesarCipher {
    
    public static String encrypt(String string, int key) {
        String newString = "";
        for (char c : string.toCharArray()) {
            newString += (char)(c + key);
        }
        return newString;
    }
    
    public static String decrypt(String string, int key) {
        String newString = "";
        for (char c : string.toCharArray()) {
            newString += (char)(c - key);
        }
        return newString;
    }
    
    public static void main(String[] args) {
        String msg = encrypt("this is cool if it really works", 2);
        String res = decrypt(msg, 2);
        System.out.println(msg);
        System.out.println(res);
    }
}