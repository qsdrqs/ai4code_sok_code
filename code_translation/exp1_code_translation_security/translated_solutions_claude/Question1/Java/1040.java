public class Encryption {
    
    /**
     * encrypt a string with a given key
     */
    public static String encrypt(String string, String key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            encrypted.append((char)(string.charAt(i) ^ key.charAt(i % key.length())));
        }
        return encrypted.toString();
    }
    
    /**
     * decrypt a string with a given key
     */
    public static String decrypt(String string, String key) {
        return encrypt(string, key);
    }
    
    public static void main(String[] args) {
        // Test assertion equivalent
        String original = "sadasdasdsa";
        String key = "1234";
        String encrypted = encrypt(original, key);
        String decrypted = decrypt(encrypted, key);
        
        assert decrypted.equals(original) : "Decryption failed!";
        
        // Alternative without assertions (more common in Java)
        if (!decrypted.equals(original)) {
            throw new AssertionError("Decryption failed!");
        }
        
        System.out.println("Test passed successfully!");
    }
}