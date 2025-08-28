/**
 * Utility class that provides very simple character–code based
 * encryption / de-encryption that behaves exactly like the
 * Python version shown in the question.
 *
 * NOTE:  This “cipher” is *not* secure; it is just a literal
 *        port of the example Python code.
 */
public class Main {

    /**
     * Encrypt every character by adding the key to its
     * Unicode code-point (identical to the Python version).
     */
    public static String encrypt(String text, int key) {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            // promote to int, add key, cast back to char
            sb.append((char) (c + key));
        }
        return sb.toString();
    }

    /**
     * Decrypt every character by subtracting the key from its
     * Unicode code-point (inverse of encrypt above).
     */
    public static String decrypt(String text, int key) {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            sb.append((char) (c - key));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("name", 1234));   // prints: ՀԳԿԷ
        System.out.println(decrypt("ՀԳԿԷ", 1234));  // prints: name
    }
}