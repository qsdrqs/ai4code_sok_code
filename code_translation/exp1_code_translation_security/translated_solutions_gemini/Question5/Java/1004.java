/**
 * This class contains the Java translation of the provided C code.
 * All dependencies are included within this class.
 */
public class IntegerRepresentation {

    /**
     * Function repr
     * <p>
     * Takes in a signed integer and returns the string representation of that integer,
     * with commas as thousands separators.
     *
     * @param n The integer to convert.
     * @return The string representation of the integer.
     */
    public static String repr(int n) {
        // When multiplied by -1, Integer.MIN_VALUE overflows, so handle it separately.
        if (n == Integer.MIN_VALUE) {
            return "-2,147,483,648";
        }

        if (n == 0) {
            return "0";
        }

        // Use StringBuilder for efficient, mutable string construction.
        StringBuilder sb = new StringBuilder();
        
        boolean isNegative = false;
        if (n < 0) {
            isNegative = true;
            n *= -1; // Safe to do now that MIN_VALUE is handled.
        }

        int digitCount = 0;
        while (n > 0) {
            // Append the last digit of the number.
            sb.append(n % 10);
            digitCount++;
            
            // Add a comma after every 3 digits, but not at the very end.
            // The (n / 10 > 0) check ensures there are more digits to come.
            if (digitCount % 3 == 0 && (n / 10) > 0) {
                sb.append(',');
            }
            
            // Remove the last digit.
            n /= 10;
        }

        if (isNegative) {
            sb.append('-');
        }

        // The string was built backwards, so reverse it to get the correct order.
        sb.reverse();

        return sb.toString();
    }

    /*
    // The C `reverse` function is no longer needed due to StringBuilder.reverse().
    // For educational purposes, here is how it would be translated directly:
    
    /**
     * Function reverse
     * <p>
     * Takes in a StringBuilder and reverses it in place.
     *
     * @param sb The StringBuilder to reverse.
     */
    private static void reverse(StringBuilder sb) {
        int i = 0;
        int j = sb.length() - 1;
        while (i < j) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
            i++;
            j--;
        }
    }
    */

    /**
     * The main method to run and test the repr function.
     */
    public static void main(String[] args) {
        System.out.println("--- Testing the repr function ---");

        int n1 = 0;
        String str1 = repr(n1);
        System.out.println("Input: " + n1 + " -> Output: " + str1);

        int n2 = 1234567;
        String str2 = repr(n2);
        System.out.println("Input: " + n2 + " -> Output: " + str2);

        int n3 = -98765;
        String str3 = repr(n3);
        System.out.println("Input: " + n3 + " -> Output: " + str3);
        
        int n4 = 100;
        String str4 = repr(n4);
        System.out.println("Input: " + n4 + " -> Output: " + str4);

        int n5 = Integer.MAX_VALUE;
        String str5 = repr(n5);
        System.out.println("Input: " + n5 + " -> Output: " + str5);

        int n6 = Integer.MIN_VALUE;
        String str6 = repr(n6);
        System.out.println("Input: " + n6 + " -> Output: " + str6);
    }
}