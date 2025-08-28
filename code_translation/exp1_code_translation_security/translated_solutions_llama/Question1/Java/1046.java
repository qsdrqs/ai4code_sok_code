public class Main {

    /**
     * Encrypts a message by inserting a key between each character.
     * 
     * @param msg  The message to be encrypted.
     * @param key  The key to be inserted.
     * @return     The encrypted message.
     */
    public static String encrypt(String msg, String key) {
        StringBuilder temp = new StringBuilder();
        for (char c : msg.toCharArray()) {
            temp.append(c).append(key);
            // System.out.println(temp.toString()); // Uncomment for debugging
        }
        return temp.toString();
    }

    /**
     * Decrypts a message by removing the key inserted between each character.
     * 
     * @param msg  The message to be decrypted.
     * @param key  The key that was inserted.
     * @return     The decrypted message.
     */
    public static String decrypt(String msg, String key) {
        int control = 0;
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < msg.length(); i++) {
            if (control % 2 == 0) {
                temp.append(msg.charAt(i));
            }
            control++;
        }
        return temp.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt('a' + "", "b")); // Note: In Java, char is not directly convertible to String, so we use 'a' + ""
        System.out.println(decrypt("ab", "b"));
    }
}