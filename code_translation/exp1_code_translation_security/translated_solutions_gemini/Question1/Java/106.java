/**
 * This class provides methods to encrypt and decrypt text using the Vigenère cipher.
 * The Vigenère cipher is a method of encrypting alphabetic text by using a series of
 * interwoven Caesar ciphers, based on the letters of a keyword.
 *
 * This implementation works with lowercase English alphabet characters ('a' through 'z').
 */
public class VigenereCipher {

    /**
     * Encrypts a given plaintext string using a key with the Vigenère cipher.
     *
     * @param plaintext The lowercase string to be encrypted.
     * @param key The lowercase key to use for encryption.
     * @return The resulting ciphertext string.
     */
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < plaintext.length(); i++) {
            // Get the numeric value of the characters (a=0, b=1, ...)
            int plainCharValue = plaintext.charAt(i) - 'a';
            int keyCharValue = key.charAt(i % keyLength) - 'a';

            // Apply the Vigenère encryption formula: C = (P + K) mod 26
            int encryptedCharValue = (plainCharValue + keyCharValue) % 26;

            // Convert the numeric value back to a character and append it
            ciphertext.append((char) (encryptedCharValue + 'a'));
        }
        return ciphertext.toString();
    }

    /**
     * Decrypts a given ciphertext string using a key with the Vigenère cipher.
     *
     * @param ciphertext The lowercase string to be decrypted.
     * @param key The lowercase key used for the original encryption.
     * @return The resulting plaintext string.
     */
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0; i < ciphertext.length(); i++) {
            // Get the numeric value of the characters (a=0, b=1, ...)
            int cipherCharValue = ciphertext.charAt(i) - 'a';
            int keyCharValue = key.charAt(i % keyLength) - 'a';

            // Apply the Vigenère decryption formula: P = (C - K + 26) mod 26
            // We add 26 to handle potential negative results from the subtraction.
            int decryptedCharValue = (cipherCharValue - keyCharValue + 26) % 26;

            // Convert the numeric value back to a character and append it
            plaintext.append((char) (decryptedCharValue + 'a'));
        }
        return plaintext.toString();
    }

    /**
     * The main method to demonstrate the encryption and decryption process.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        String plaintext = "helloworld";
        String key = "python";

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);

        String decrypted = decrypt(ciphertext, key);
        System.out.println(decrypted);

        // In Java, assertions must be enabled with the -ea (enable assertions) flag.
        // For example, to run this from the command line: java -ea VigenereCipher
        assert plaintext.equals(decrypted);
        System.out.println("Assertion passed: Decrypted text matches the original plaintext.");
    }
}