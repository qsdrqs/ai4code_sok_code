public class CryptoUtils {
    
    public static String encrypt(Object key, Object string) {
        // Type checking
        if (!(key instanceof Integer)) {
            return null;
        }
        if (!(string instanceof String)) {
            return null;
        }
        
        int keyInt = (Integer) key;
        String stringStr = (String) string;
        
        // Validation checks
        if (keyInt < 0) {
            return null;
        }
        if (stringStr.length() == 0) {
            return null;
        }
        if (keyInt > 26) {
            keyInt = keyInt % 26;
        }
        
        StringBuilder newString = new StringBuilder();
        
        for (int i = 0; i < stringStr.length(); i++) {
            char c = stringStr.charAt(i);
            
            if (Character.isLetter(c)) {
                if (Character.isLowerCase(c)) {
                    newString.append((char)((c - 'a' + keyInt) % 26 + 'a'));
                } else {
                    newString.append((char)((c - 'A' + keyInt) % 26 + 'A'));
                }
            } else {
                newString.append(c);
            }
        }
        
        return newString.toString();
    }
    
    /**
     * function that decrypts a given string using a symmetric key
     */
    public static String decrypt(Object ciphertext, Object key) {
        // Type checking
        if (!(ciphertext instanceof String) || !(key instanceof String)) {
            return null;
        }
        
        String ciphertextStr = (String) ciphertext;
        String keyStr = (String) key;
        
        // Validation checks
        if (ciphertextStr.length() == 0 || keyStr.length() == 0) {
            return null;
        }
        if (keyStr.length() == 1) {
            return ciphertextStr;
        }
        if (keyStr.length() > ciphertextStr.length()) {
            return null;
        }
        
        int keyLen = keyStr.length();
        int[] keyAscii = new int[keyLen];
        for (int i = 0; i < keyLen; i++) {
            keyAscii[i] = (int) keyStr.charAt(i);
        }
        
        int[] ciphertextAscii = new int[ciphertextStr.length()];
        for (int i = 0; i < ciphertextStr.length(); i++) {
            ciphertextAscii[i] = (int) ciphertextStr.charAt(i);
        }
        
        int ciphertextLen = ciphertextAscii.length;
        StringBuilder plaintext = new StringBuilder();
        
        for (int i = 0; i < ciphertextLen; i++) {
            int value = (ciphertextAscii[i] - keyAscii[i % keyLen]) % 26;
            plaintext.append((char)(value + 65));
        }
        
        return plaintext.toString();
    }
}