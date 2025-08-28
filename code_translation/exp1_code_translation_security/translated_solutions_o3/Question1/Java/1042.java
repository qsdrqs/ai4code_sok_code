public class CryptoUtils {

    /**
     * Caesar–style encryption that mimics the Python implementation shown in the prompt.
     *
     * @param key    shift amount; must be a non-negative integer
     * @param text   string to encrypt
     * @return the encrypted string, or {@code null} when the same cases that return
     *         None in the original Python code are encountered
     */
    public static String encrypt(Integer key, String text) {
        /* --- argument checks (mirror Python logic) ------------------------- */
        if (key == null || text == null) {
            return null;
        }
        if (key < 0 || text.isEmpty()) {
            return null;
        }

        /* reduce any key larger than alphabet length */
        if (key > 26) {
            key = key % 26;
        }

        StringBuilder result = new StringBuilder(text.length());

        for (char c : text.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                if (Character.isLowerCase(c)) {
                    /* 'a' == 97 */
                    result.append((char) ((c - 'a' + key) % 26 + 'a'));
                } else {
                    /* 'A' == 65 */
                    result.append((char) ((c - 'A' + key) % 26 + 'A'));
                }
            } else {
                /* keep punctuation, digits, spaces … unchanged */
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * Symmetric-key decryption (Vigenère-like) that reproduces the behaviour of the
     * Python `decrypt` function from the prompt.
     *
     * @param ciphertext  the text to decrypt
     * @param key         decryption key (string)
     * @return plain text, or {@code null} under the same conditions that the
     *         original Python function returns None
     */
    public static String decrypt(String ciphertext, String key) {
        /* --- argument checks ------------------------------------------------ */
        if (ciphertext == null || key == null) {
            return null;
        }
        if (ciphertext.isEmpty() || key.isEmpty()) {
            return null;
        }
        if (key.length() == 1) {
            return ciphertext;          // nothing to do
        }
        if (key.length() > ciphertext.length()) {
            return null;
        }

        /* pre-compute ASCII values of the key characters */
        int keyLen = key.length();
        int[] keyAscii = new int[keyLen];
        for (int i = 0; i < keyLen; i++) {
            keyAscii[i] = key.charAt(i);
        }

        StringBuilder plain = new StringBuilder(ciphertext.length());
        for (int i = 0; i < ciphertext.length(); i++) {
            int cipherVal = ciphertext.charAt(i);
            int value = (cipherVal - keyAscii[i % keyLen]) % 26;
            if (value < 0) {        // Java’s % keeps the sign, so adjust negatives
                value += 26;
            }
            plain.append((char) (value + 65));   // 65 == 'A'
        }
        return plain.toString();
    }
}