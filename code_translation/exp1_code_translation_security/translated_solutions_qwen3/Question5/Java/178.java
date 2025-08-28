public class Main {
    public static void main(String[] args) {
        System.out.print(convertToString(2000));
    }

    public static String convertToString(int num) {
        // Convert the number to a string
        String s = Integer.toString(num);
        int space = s.length();

        // Calculate number of commas (rounding s.length() / 3)
        int numOfCommas = (int) Math.round((double) space / 3);
        System.out.println(numOfCommas);

        // Allocate space for the new string with commas
        char[] newString = new char[space + numOfCommas];

        // Loop through the indices up to (space + numOfCommas)
        for (int i = 0; i < space + numOfCommas; i++) {
            // Copy character from original string if within bounds
            if (i < s.length()) {
                newString[i] = s.charAt(i);
            }

            // Print the character (debugging)
            if (i < s.length()) {
                System.out.print(s.charAt(i));
            }

            // Insert comma at every 3rd index
            if (i % 3 == 0 && i + 1 < newString.length) {
                System.out.print("s");
                newString[i + 1] = ',';
            }
        }

        return new String(newString);
    }
}