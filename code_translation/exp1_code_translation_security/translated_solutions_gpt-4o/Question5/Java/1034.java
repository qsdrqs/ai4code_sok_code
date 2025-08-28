import java.util.Random;

public class Main {
    public static void main(String[] args) {
        long value;
        int c1, v1, len;
        char[] vstring = new char[11];
        char[] commas = new char[14];

        // Initialize random number generator
        Random rand = new Random();
        value = Math.abs(rand.nextLong());

        System.out.println("Before: \t" + value);

        // Convert the value to a string
        String valueString = Long.toString(value);
        vstring = valueString.toCharArray();

        len = vstring.length;
        c1 = v1 = 0;

        while (v1 < vstring.length) {
            if (len % 3 != 0) {
                commas[c1] = vstring[v1];
            } else {
                if (c1 != 0) {
                    commas[c1] = ',';
                    c1++;
                }
                commas[c1] = vstring[v1];
            }
            c1++;
            v1++;
            len--;
        }

        // Convert the commas array to a string
        String result = new String(commas, 0, c1);
        System.out.println("With: \t" + result);
    }
}