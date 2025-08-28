public class VigenereCipher {
    
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char encryptedChar = (char)((plaintext.charAt(i) + key.charAt(i % key.length()) - 2 * 'a') % 26 + 'a');
            ciphertext.append(encryptedChar);
        }
        return ciphertext.toString();
    }
    
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char decryptedChar = (char)((ciphertext.charAt(i) - key.charAt(i % key.length()) + 26) % 26 + 'a');
            plaintext.append(decryptedChar);
        }
        return plaintext.toString();
    }
    
    public static void main(String[] args) {
        String plaintext = "helloworld";
        String key = "python";
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);
        String decrypted = decrypt(ciphertext, key);
        System.out.println(decrypted);
        assert plaintext.equals(decrypted);
    }
}