public class CaesarCipher {

    /* -----------------------------  encrypt  ----------------------------- */

    public static String encrypt(String str, int key) {
        char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        StringBuilder newString = new StringBuilder();

        for (char c : str.toCharArray()) {

            /* keep the space exactly like the Python version */
            if (c == ' ') {
                newString.append(' ');
                continue;
            }

            /* locate the character inside the alphabet array */
            int alphaIndex = indexOf(alpha, c);
            System.out.println(alphaIndex);         // prints the index – same as Python

            /* deal with the wrap-around logic identical to the Python code */
            if (alphaIndex + key > alpha.length - 1) {
                alphaIndex = key - (alpha.length - 1 - alphaIndex);
            }

            newString.append(alpha[alphaIndex + key]);
        }

        return newString.toString();
    }

    /* -----------------------------  decrypt  ----------------------------- */

    public static String decrypt(String str, int key) {
        char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();

        StringBuilder newString = new StringBuilder();

        for (char c : str.toCharArray()) {

            /* spaces are skipped exactly as in the original Python function */
            if (c == ' ') {
                continue;
            }

            int alphaIndex = indexOf(alpha, c);

            newString.append(alpha[alphaIndex - key]);
        }

        return newString.toString();
    }

    /* ---------------------------  helper method  ------------------------- */

    private static int indexOf(char[] source, char target) {
        for (int i = 0; i < source.length; i++) {
            if (source[i] == target) {
                return i;
            }
        }
        /* if character is not found we return -1,
           but the original Python code assumes valid lowercase input */
        return -1;
    }

    /* ------------------------------  driver  ----------------------------- */

    public static void main(String[] args) {

        String encrypted = encrypt("hello worlz", 3);
        System.out.println(encrypted);

        String decrypted = decrypt(encrypted, 3);
        System.out.println(decrypted);
    }
}