public class SimpleCipher {

    // Equivalent to Python's encrypt function
    public static String encrypt(String text, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            encrypted.append((char) (c + key));   // shift each character forward
        }
        return encrypted.toString();
    }

    // Equivalent to Python's decrypt function
    public static String decrypt(String text, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            decrypted.append((char) (c - key));   // shift each character back
        }
        return decrypted.toString();
    }

    public static void main(String[] args) {
        String msg = encrypt("this is cool if it really works", 2);
        String res = decrypt(msg, 2);

        System.out.println(msg);  // Encrypted text
        System.out.println(res);  // Decrypted (original) text
    }
}