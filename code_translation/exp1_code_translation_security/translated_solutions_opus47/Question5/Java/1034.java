import java.util.Random;

public class Main {
    public static void main(String[] args) {
        long value;
        int c1, v1, len;
        
        Random rand = new Random();           // equivalent to srand(time(NULL))
        value = rand.nextInt(Integer.MAX_VALUE); // equivalent to rand() (non-negative int)
        
        System.out.printf("Before: \t%d\n", value);
        String vstring = String.valueOf(value);  // equivalent to sprintf
        StringBuilder commas = new StringBuilder();
        
        len = vstring.length();
        c1 = v1 = 0;
        
        while (v1 < vstring.length()) {       // equivalent to while(vstring[v1])
            if (len % 3 != 0) {
                commas.append(vstring.charAt(v1));
            } else {
                if (c1 != 0) {
                    commas.append(',');
                    c1++;
                }
                commas.append(vstring.charAt(v1));
            }
            c1++;
            v1++;
            len--;
        }
        
        System.out.printf("with: \t%s\n", commas.toString());
    }
}