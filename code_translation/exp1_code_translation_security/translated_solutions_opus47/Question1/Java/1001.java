public class Cipher {
    public static String encrypt(String key, String string) {
        StringBuilder encryptedText = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char keyC = key.charAt(i % key.length());
            char encryptedC = (char) ((string.charAt(i) + keyC) % 256);
            encryptedText.append(encryptedC);
        }
        return encryptedText.toString();
    }

    public static String decrypt(String key, String string) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char keyC = key.charAt(i % key.length());
            char decryptedC = (char) ((string.charAt(i) - keyC + 256) % 256);
            decryptedText.append(decryptedC);
        }
        return decryptedText.toString();
    }
}