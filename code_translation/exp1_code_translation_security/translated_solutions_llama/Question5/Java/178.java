public class Main {

    /**
     * Converts an integer into a string with commas as thousand separators.
     * 
     * @param num The number to be converted.
     * @return A string representation of the number with commas.
     */
    public static String convertToString(int num) {
        String string = Integer.toString(num);
        int numOfCommas = (string.length() - 1) / 3;
        StringBuilder newString = new StringBuilder();

        for (int i = string.length() - 1; i >= 0; i--) {
            newString.insert(0, string.charAt(i));
            if ((string.length() - i) % 3 == 0 && i != 0) {
                newString.insert(0, ",");
            }
        }

        return newString.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToString(2000));
    }
}