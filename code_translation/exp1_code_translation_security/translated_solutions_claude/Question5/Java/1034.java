import java.util.Random;

public class Main {
    public static void main(String[] args) {
        long value;
        int c1, v1, len;
        char[] vstring = new char[11];
        char[] commas = new char[14];
        
        Random rand = new Random();
        value = Math.abs(rand.nextLong() % Integer.MAX_VALUE); // Simulate rand() behavior
        
        System.out.printf("Before: \t%d%n", value);
        String valueStr = String.valueOf(value);
        
        // Copy string to char array
        for (int i = 0; i < valueStr.length(); i++) {
            vstring[i] = valueStr.charAt(i);
        }
        
        len = valueStr.length();
        c1 = v1 = 0;
        
        while (v1 < valueStr.length() && vstring[v1] != '\0') {
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
        
        // Convert char array to string for printing
        String result = new String(commas, 0, c1);
        System.out.printf("With: \t%s%n", result);
    }
}