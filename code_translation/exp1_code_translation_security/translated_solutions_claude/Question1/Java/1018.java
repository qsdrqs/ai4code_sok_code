public class Main {
    public static void main(String[] args) {
        // Generate a key
        String key = EncryptionUtil.generateKey();
        
        // Encrypt
        String plaintext = "Hello, World!";
        String encrypted = EncryptionUtil.encrypt(plaintext, key);
        
        // Decrypt
        String decrypted = EncryptionUtil.decrypt(encrypted, key);
        
        System.out.println("Original: " + plaintext);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}