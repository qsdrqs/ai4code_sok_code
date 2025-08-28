public class SymmetricEncryption {

    public static String encrypt(String string, String key) {
        // Encrypts a given string using a given symmetric key.
        StringBuilder encryptedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            encryptedString.append((char) (string.charAt(i) ^ key.charAt(i % key.length())));
        }
        return encryptedString.toString();
    }

    public static String decrypt(String string, String key) {
        // Decrypts a given string using a given symmetric key.
        StringBuilder decryptedString = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            decryptedString.append((char) (string.charAt(i) ^ key.charAt(i % key.length())));
        }
        return decryptedString.toString();
    }

    public static void main(String[] args) {
        String original = "Hello, World!";
        String key = "key";
        
        String encrypted = encrypt(original, key);
        System.out.println("Encrypted: " + encrypted);
        
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }
}