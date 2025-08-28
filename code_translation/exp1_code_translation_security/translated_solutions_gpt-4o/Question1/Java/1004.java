public class XOREncryption {

    /**
     * Function encrypt
     *
     * Given a message and a key, encrypt the message using XOR encryption.
     */
    public static String encrypt(String message, String key) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            encrypted.append((char) (message.charAt(i) ^ key.charAt(i % key.length())));
        }
        return encrypted.toString();
    }

    /**
     * Function decrypt
     *
     * Given an encrypted message and a key, decrypt the message using XOR encryption.
     */
    public static String decrypt(String encrypted, String key) {
        return encrypt(encrypted, key);
    }

    public static void main(String[] args) {
        String message = "Hello world";
        String key = "key";
        String encryptedMessage = encrypt(message, key);
        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println(decryptedMessage);
    }
}