public class Main {

    /**
     * Formats the given integer with commas as thousand separators.
     * 
     * @param input The number to be formatted.
     * @return A string representation of the input number with commas.
     */
    public static String change(int input) {
        // Convert the integer to a string to easily get its length
        String strInput = Integer.toString(input);
        int count = strInput.length();
        
        // Calculate the total length of the result string
        int num = count + (count - 1) / 3;
        
        // Initialize a StringBuilder for efficient string construction
        StringBuilder result = new StringBuilder(num);
        
        // Initialize a counter for thousand separators
        int mid = 0;
        
        // Iterate over the input string from right to left
        for (int i = count - 1; i >= 0; i--) {
            result.insert(0, strInput.charAt(i));
            mid++;
            
            // Add a comma every three digits
            if (mid == 3 && i != 0) {
                result.insert(0, ',');
                mid = 0;
            }
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        String snum = change(700000000);
        System.out.println(snum);
    }
}