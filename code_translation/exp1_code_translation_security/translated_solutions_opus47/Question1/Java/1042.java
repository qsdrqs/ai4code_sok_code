public class Cipher {

    public static String encrypt(Integer key, String string) {
        if (key == null) {
            return null;
        }
        if (string == null) {
            return null;
        }
        if (key < 0) {
            return null;
        }
        if (string.length() == 0) {
            return null;
        }
        if (key > 26) {
            key = key % 26;
        }
        StringBuilder newString = new StringBuilder();
        for (int idx = 0; idx < string.length(); idx++) {
            char c = string.charAt(idx);
            if (Character.isLetter(c)) {
                if (Character.isLowerCase(c)) {
                    newString.append((char) ((c - 97 + key) % 26 + 97));
                } else {
                    newString.append((char) ((c - 65 + key) % 26 + 65));
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
    public static String decrypt(String ciphertext, String key) {
        if (ciphertext == null || key == null) {
            return null;
        }
        if (ciphertext.length() == 0 || key.length() == 0) {
            return null;
        }
        if (key.length() == 1) {
            return ciphertext;
        }
        if (key.length() > ciphertext.length()) {
            return null;
        }
        int keyLen = key.length();
        int[] keyAscii = new int[keyLen];
        for (int i = 0; i < keyLen; i++) {
            keyAscii[i] = (int) key.charAt(i);
        }
        int ciphertextLen = ciphertext.length();
        int[] ciphertextAscii = new int[ciphertextLen];
        for (int i = 0; i < ciphertextLen; i++) {
            ciphertextAscii[i] = (int) ciphertext.charAt(i);
        }
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertextLen; i++) {
            int value = Math.floorMod(ciphertextAscii[i] - keyAscii[i % keyLen], 26);
            plaintext.append((char) (value + 65));
        }
        return plaintext.toString();
    }
}