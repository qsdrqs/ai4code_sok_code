public class EncryptDecrypt {

    // Method to encrypt the plaintext with the given key
    public static String encrypt(String key, String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char keyChar = key.charAt(i % key.length());
            char cipherChar = (char) ((plaintext.charAt(i) + keyChar) % 256);
            ciphertext.append(cipherChar);
        }
        return ciphertext.toString();
    }

    // Method to decrypt the ciphertext with the given key
    public static String decrypt(String key, String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char keyChar = key.charAt(i % key.length());
            char plainChar = (char) ((256 + ciphertext.charAt(i) - keyChar) % 256);
            plaintext.append(plainChar);
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        String key = "123";
        String plaintext = "helloworld";
        
        // Encrypt the plaintext
        String encryptedText = encrypt(key, plaintext);
        
        // Decrypt the ciphertext
        String decryptedText = decrypt(key, encryptedText);
        
        // Print the decrypted text
        System.out.println(decryptedText);
    }
}