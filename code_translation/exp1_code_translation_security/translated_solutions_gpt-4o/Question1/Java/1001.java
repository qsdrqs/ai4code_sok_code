public class Cipher {

    public static String encrypt(String key, String text) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char keyChar = key.charAt(i % key.length());
            char encryptedChar = (char) ((text.charAt(i) + keyChar) % 256);
            encryptedText.append(encryptedChar);
        }
        return encryptedText.toString();
    }

    public static String decrypt(String key, String text) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char keyChar = key.charAt(i % key.length());
            char decryptedChar = (char) ((text.charAt(i) - keyChar + 256) % 256);
            decryptedText.append(decryptedChar);
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        String key = "mysecretkey";
        String originalText = "Hello, World!";
        
        String encrypted = encrypt(key, originalText);
        System.out.println("Encrypted: " + encrypted);
        
        String decrypted = decrypt(key, encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}