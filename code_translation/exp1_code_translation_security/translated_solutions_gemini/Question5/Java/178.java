import java.lang.Math;

/**
 * A direct translation of the provided C code.
 * NOTE: The original C code is severely flawed. This translation
 * preserves the flawed logic to be as faithful as possible.
 */
public class CCodeTranslator {

    public static void main(String[] args) {
        // The C main function calls the method and prints the result.
        // We add a final newline for cleaner terminal output.
        System.out.println(convertToString(2000));
    }

    /**
     * This is a direct translation of the C function, including its logical flaws.
     * @param num The integer to convert.
     * @return A string representation, incorrectly formatted with commas.
     */
    public static String convertToString(int num) {
        // C: char* string = ...; snprintf(string, ...);
        // Java: Convert int to String. This is much simpler.
        String string = Integer.toString(num);

        // C: int numOfCommas = round(strlen(string)/3);
        // Java: Replicating the same (flawed) logic for calculating commas.
        // We cast to float to ensure floating-point division before rounding.
        int numOfCommas = Math.round((float)string.length() / 3);

        // C: printf("%i\n", numOfCommas);
        // Java: Replicating the debug print.
        System.out.println(numOfCommas);

        // The C loop for building the new string is broken. It reads past buffer
        // ends and has incorrect comma insertion logic. A direct translation of
        // memory errors is not possible. Instead, we translate the flawed algorithm:
        // iterating from the left and inserting a comma every 3 characters.
        StringBuilder newString = new StringBuilder();

        // We will also replicate the debug prints from inside the C loop.
        System.out.print("Debug output from loop: ");

        for (int i = 0; i < string.length(); i++) {
            // C: newString[i] = string[i];
            // Java: Append the character.
            newString.append(string.charAt(i));

            // C: printf("%d", string[i]);
            // Java: Replicating the debug print of the character's ASCII value.
            System.out.print((int)string.charAt(i) + " ");

            // C: if (i % 3 == 0 && i != strlen(string) + 1 + numOfCommas) { ... }
            // Java: Replicating the flawed "every 3rd char from the left" logic.
            // We add a check to avoid adding a comma at the very end.
            if (i % 3 == 0 && i < string.length() - 1) {
                // C: printf("s");
                // Java: Replicating the debug print.
                System.out.print("s ");

                // C: newString[i + 1] = ","; (This was buggy, overwriting the next char)
                // Java: We append the comma, which is the non-destructive equivalent.
                newString.append(",");
            }
        }
        // Add a newline to separate the debug output from the final result.
        System.out.println();

        // C: return newString;
        // Java: Return the built string.
        return newString.toString();
    }
}