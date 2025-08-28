public class Main {
    public static void main(String[] args) {
        String original = "Hello, World!";
        String encrypted = EncryptionUtil.encryptText(original);
        String decrypted = EncryptionUtil.decryptText(encrypted);
        
        System.out.println("Original: " + original);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}