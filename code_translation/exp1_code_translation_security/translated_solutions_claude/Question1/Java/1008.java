public class Cipher {
    
    /**
     * encrypt with key
     */
    public static String encrypt(String key, String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char keyC = key.charAt(i % key.length());
            char cipherC = (char)((plaintext.charAt(i) + keyC) % 256);
            ciphertext.append(cipherC);
        }
        return ciphertext.toString();
    }
    
    /**
     * decrypt with key
     */
    public static String decrypt(String key, String ciphertext) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            char keyC = key.charAt(i % key.length());
            char plainC = (char)((256 + ciphertext.charAt(i) - keyC) % 256);
            plaintext.append(plainC);
        }
        return plaintext.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(decrypt("123", encrypt("123", "helloworld")));
    }
}