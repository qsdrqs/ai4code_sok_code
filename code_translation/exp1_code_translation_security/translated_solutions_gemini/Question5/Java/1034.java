import java.util.Random;

public class NumberFormatter {

    public static void main(String[] args) {
        // C: long value;
        long value;

        // C: int c1, v1, len;
        // We only need v1 and len, as the StringBuilder handles its own length (c1).
        int v1, len;

        // C: char vstring[11];
        String vstring;

        // C: char commas[14];
        // A StringBuilder is the Java equivalent for building a string in a loop.
        StringBuilder commas = new StringBuilder();

        // C: srand((unsigned)time(NULL));
        //    value = rand();
        // In Java, creating a new Random object seeds it automatically.
        // C's rand() often returns an int, so we use nextInt() for a similar result.
        Random rand = new Random();
        value = rand.nextInt(Integer.MAX_VALUE); // Generates a non-negative random int

        // C: printf("Before: \t%ld\n", value);
        System.out.println("Before: \t" + value);

        // C: sprintf(vstring, "%ld", value);
        vstring = Long.toString(value);

        // C: len = strlen(vstring);
        len = vstring.length();

        // C: c1 = v1 = 0;
        v1 = 0;

        // C: while(vstring[v1])
        // The C loop runs until it hits the null terminator.
        // The Java equivalent is to loop while the index is less than the string's length.
        while (v1 < vstring.length()) {
            // This is the core logic for placing commas. A comma is added
            // before a digit if the number of remaining digits is a multiple of 3.
            if (len % 3 != 0) { // C: if(len % 3)
                commas.append(vstring.charAt(v1));
            } else {
                // C: if(c1 != 0)
                // Don't add a comma at the very beginning of the string.
                if (commas.length() != 0) {
                    commas.append(',');
                }
                commas.append(vstring.charAt(v1));
            }
            // Move to the next character and decrement the remaining length counter.
            v1++;
            len--;
        }

        // C: printf(with: \t%s\n", commas);
        // Note: The original C code has a syntax error in this printf statement.
        // It has been corrected here.
        System.out.println("With:   \t" + commas.toString());
    }
}