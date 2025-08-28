public class Main {

    // Helper function to insert a comma at the specified index in the string
    public static String insert(String str, int index) {
        if (index < 0 || index > str.length()) {
            return str; // Return original string if index is out of bounds
        }
        return str.substring(0, index) + ',' + str.substring(index);
    }

    // Function to convert an integer to a string with commas inserted
    public static String stringRepre(int num) {
        String strnum = Integer.toString(num);
        int l_original = strnum.length();
        int conum = l_original / 3;
        int l_new = l_original + conum; // Total length after inserting commas

        String buff = strnum;

        for (int i = 1; i <= conum; i++) {
            int index = l_new - 3 + (i - 1);
            buff = insert(strnum, index); // Insert into the original string each time
        }

        return buff;
    }

    // Main method
    public static void main(String[] args) {
        // Equivalent to C's main(argc, argv)
        // args.length corresponds to argc
        String str = stringRepre(args.length);
        System.out.println(str); // Print the result (since Java main can't return a value)
    }
}