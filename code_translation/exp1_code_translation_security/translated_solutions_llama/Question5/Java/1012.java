public class Main {

    /**
     * Helper function to insert a comma into a string at a specified index.
     * 
     * @param str   the original string
     * @param index the index at which to insert the comma
     * @return a new string with a comma inserted at the specified index
     */
    public static String insert(String str, int index) {
        return str.substring(0, index) + "," + str.substring(index);
    }

    /**
     * Formats an integer by inserting commas as thousand separators.
     * 
     * @param num the integer to format
     * @return a string representation of the integer with commas
     */
    public static String stringRepre(int num) {
        String strnum = Integer.toString(num);
        int length = strnum.length();
        int count = (length - 1) / 3;
        StringBuilder sb = new StringBuilder(strnum);

        for (int i = count; i > 0; i--) {
            int index = length - 3 * i;
            sb.insert(index, ",");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            try {
                int argc = Integer.parseInt(args[0]);
                String str = stringRepre(argc);
                System.out.println(str);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } else {
            System.out.println("Please provide an integer as a command-line argument.");
        }
    }
}