// All required libraries / dependencies will be supplied with the project.
import your.crypto.package.Cryptocode;   // ← placeholder import

public class CryptoDemo {

    /**
     * Encrypt plain text using a key.
     *
     * @param plainText the original string
     * @param key       the password / key
     * @return encrypted (Base-64) string
     */
    public static String encodeStr(String plainText, String key) {
        return Cryptocode.encrypt(plainText, key);
    }

    /**
     * Decrypt an encrypted string back to plain text.
     *
     * @param encodedText encrypted (Base-64) string
     * @param key         the password / key
     * @return decrypted plain text
     */
    public static String decodeStr(String encodedText, String key) {
        return Cryptocode.decrypt(encodedText, key);
    }

    public static void main(String[] args) {
        String encoded = encodeStr("I am okay", "wow");
        System.out.println(decodeStr(encoded, "wow"));
    }
}