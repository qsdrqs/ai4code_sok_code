import java.text.DecimalFormat;

public class CtoJavaConverter {

    /**
     * Converts an integer to a string, adding commas as thousands separators.
     * This correctly implements the apparent intent of the original C function.
     *
     * @param number The integer to convert.
     * @return A string representation of the number with commas (e.g., "100,000").
     */
    public static String intToStringWithCommas(int number) {
        // The most standard and reliable way to format numbers in Java is using
        // DecimalFormat or String.format. This handles all cases correctly.
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(number);

        /*
         * An alternative one-liner using String.format():
         * return String.format("%,d", number);
         */
    }

    public static void main(String[] args) {
        // This replicates the behavior of the C main function.
        System.out.println(intToStringWithCommas(100000));
        System.out.println("test");
    }
}