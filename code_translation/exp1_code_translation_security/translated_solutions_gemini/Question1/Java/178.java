import java.util.ArrayList;
import java.util.List;

public class Cipher {

    // The alphabet, defined as a constant since it's used in both methods.
    // Using a String is more idiomatic and efficient in Java than a list of characters.
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Translates the Python encrypt function.
     * Note: The wrap-around logic from the original Python code is flawed and
     * does not implement a standard Caesar cipher. This translation faithfully
     * reproduces that specific logic.
     *
     * @param str The input string to encrypt.
     * @param key The integer key for the shift.
     * @return A List of single-character strings representing the encrypted text.
     */
    public static List<String> encrypt(String str, int key) {
        // Corresponds to newString = [] in Python
        List<String> newString = new ArrayList<>();

        // Corresponds to 'for c in str:' in Python
        for (char c : str.toCharArray()) {
            // Handles spaces
            if (c == ' ') {
                newString.add(" ");
                continue;
            }

            // Corresponds to alpha.index(c)
            int alphaIndex = ALPHA.indexOf(c);

            // Corresponds to print(alphaIndex)
            System.out.println(alphaIndex);

            // This is a direct translation of the flawed wrap-around logic from Python
            if (alphaIndex + key > ALPHA.length() - 1) {
                alphaIndex = key - (ALPHA.length() - 1 - alphaIndex);
            }

            // Appends the shifted character to the list
            char encryptedChar = ALPHA.charAt(alphaIndex + key);
            newString.add(String.valueOf(encryptedChar));
        }
        return newString;
    }

    /**
     * Translates the Python decrypt function.
     * Note: The original Python code drops spaces and relies on Python's negative
     * list indexing for wrap-around. This translation reproduces that behavior.
     *
     * @param str The input list of characters (as Strings) to decrypt.
     * @param key The integer key for the shift.
     * @return A List of single-character strings representing the decrypted text.
     */
    public static List<String> decrypt(List<String> str, int key) {
        List<String> newString = new ArrayList<>();

        for (String c : str) {
            // Skips spaces, as in the original Python code
            if (c.equals(" ")) {
                continue;
            }

            int alphaIndex = ALPHA.indexOf(c);

            // In Python, a negative index like list[-2] wraps around from the end.
            // Java's charAt() would throw an error. This logic replicates the
            // Python behavior. For the provided example, this is not triggered,
            // but it makes the translation more accurate.
            int newIndex = alphaIndex - key;
            if (newIndex < 0) {
                newIndex = ALPHA.length() + newIndex;
            }
            
            char decryptedChar = ALPHA.charAt(newIndex);
            newString.add(String.valueOf(decryptedChar));
        }
        return newString;
    }

    public static void main(String[] args) {
        // Corresponds to: encrypted = encrypt("hello worlz", 3)
        List<String> encrypted = encrypt("hello worlz", 3);

        // Corresponds to: print(encrypted)
        // Java's List.toString() provides a similar output format.
        System.out.println(encrypted);

        // Corresponds to: decrypted = decrypt(encrypted, 3)
        List<String> decrypted = decrypt(encrypted, 3);

        // Corresponds to: print(decrypted)
        System.out.println(decrypted);
    }
}