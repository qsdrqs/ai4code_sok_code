public class StringEncryption {
    
    /**
     * Encrypts a string using a given key.
     */
    public static String encrypt(String string, int key) {
        String encrypted = "";
        for (int i = 0; i < string.length(); i++) {
            encrypted += (char)(string.charAt(i) + key);
        }
        return encrypted;
    }
    
    /**
     * Decrypts a string using a given key.
     */
    public static String decrypt(String string, int key) {
        String decrypted = "";
        for (int i = 0; i < string.length(); i++) {
            decrypted += (char)(string.charAt(i) - key);
        }
        return decrypted;
    }
    
    /**
     * Main function.
     */
    public static void main(String[] args) {
        String string = "Hello World!";
        int key = 5;
        String encrypted = encrypt(string, key);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}