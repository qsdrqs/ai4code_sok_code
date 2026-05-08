public class Main {
    /**
     * encrypt a string with a given key
     */
    public static String encrypt(String string, String key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            encrypted.append((char) (string.charAt(i) ^ key.charAt(i % key.length())));
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
        assert decrypt(encrypt("sadasdasdsa", "1234"), "1234").equals("sadasdasdsa");
    }
}