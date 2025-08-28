public class Encryption {

    public static String encrypt(int key, String string) {
        if (!(string instanceof String) || key < 0 || string.length() == 0) {
            return null;
        }

        if (key > 26) {
            key = key % 26;
        }

        StringBuilder newString = new StringBuilder();
        for (char i : string.toCharArray()) {
            if (Character.isAlphabetic(i)) {
                if (Character.isLowerCase(i)) {
                    newString.append((char) ((i - 'a' + key) % 26 + 'a'));
                } else {
                    newString.append((char) ((i - 'A' + key) % 26 + 'A'));
                }
            } else {
                newString.append(i);
            }
        }
        return newString.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        if (!(ciphertext instanceof String) || !(key instanceof String)) {
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
            keyAscii[i] = key.charAt(i);
        }

        int ciphertextLen = ciphertext.length();
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertextLen; i++) {
            int value = (ciphertext.charAt(i) - keyAscii[i % keyLen]) % 26;
            if (value < 0) {
                value += 26; // Handle negative values
            }
            plaintext.append((char) (value + 'A'));
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        // Example usage
        String encrypted = encrypt(3, "HelloWorld");
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt("KHOORZRUOG", "KEY");
        System.out.println("Decrypted: " + decrypted);
    }
}