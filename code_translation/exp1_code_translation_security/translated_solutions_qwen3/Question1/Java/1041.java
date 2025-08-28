public class Encryption {

    // Encrypts a string by shifting each character's ASCII value by the given key
    public static String encrypt(String string, int key) {
        String newString = "";
        for (char c : string.toCharArray()) {
            newString += (char) (c + key);
        }
        return newString;
    }

    // Decrypts a string by shifting each character's ASCII value back by the given key
    public static String decrypt(String string, int key) {
        String newString = "";
        for (char c : string.toCharArray()) {
            newString += (char) (c - key);
        }
        return newString;
    }

    // Main method to test the encryption and decryption
    public static void main(String[] args) {
        String msg = encrypt("this is cool if it really works", 2);
        String res = decrypt(msg, 2);

        System.out.println("Encrypted: " + msg);
        System.out.println("Decrypted: " + res);
    }
}