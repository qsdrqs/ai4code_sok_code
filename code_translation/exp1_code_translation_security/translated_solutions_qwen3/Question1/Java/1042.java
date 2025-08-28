public class Cipher {

    public static String encrypt(int key, String string) {
        if (key < 0) {
            return null;
        }
        if (string == null || string.isEmpty()) {
            return null;
        }
        if (key > 26) {
            key %= 26;
        }

        StringBuilder newString = new StringBuilder();
        for (char c : string.toCharArray()) {
            if (Character.isLetter(c)) {
                if (Character.isLowerCase(c)) {
                    newString.append((char) ((c - 'a' + key) % 26 + 'a'));
                } else {
                    newString.append((char) ((c - 'A' + key) % 26 + 'A'));
                }
            } else {
                newString.append(c);
            }
        }
        return newString.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        if (ciphertext == null || key == null) {
            return null;
        }
        if (ciphertext.isEmpty() || key.isEmpty()) {
            return null;
        }
        if (key.length() == 1) {
            return ciphertext;
        }
        if (key.length() > ciphertext.length()) {
            return null;
        }

        int keyLen = key.length();
        int cipherLen = ciphertext.length();
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < cipherLen; i++) {
            char cipherChar = ciphertext.charAt(i);
            char keyChar = key.charAt(i % keyLen);
            int diff = cipherChar - keyChar;
            int mod = Math.floorMod(diff, 26);
            plaintext.append((char) (mod + 'A'));
        }

        return plaintext.toString();
    }
}