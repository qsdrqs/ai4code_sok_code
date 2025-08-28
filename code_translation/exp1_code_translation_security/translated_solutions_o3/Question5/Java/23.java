public class Main {

    /* Equivalent of   char* int_to_string(int x)   */
    public static String intToString(int x) {

        // In C the author used sprintf; in Java we can do the same with Integer.toString
        String tempString = Integer.toString(x);          // e.g. "100000"

        // C-code tried to calculate the length beforehand; not necessary in Java
        StringBuilder result = new StringBuilder();

        // Copy characters; insert a comma every third character
        for (int i = 0; i < tempString.length(); i++) {
            if (i % 3 == 0) {          // “yes comma”
                result.append(',');
            }
            result.append(tempString.charAt(i));
        }

        return result.toString();
    }

    /*
     * Equivalent of the C main-function
     */
    public static void main(String[] args) {

        System.out.print(intToString(100000));  // prints ",100,000"
        System.out.print("test\n");             // prints "test" + newline
    }
}