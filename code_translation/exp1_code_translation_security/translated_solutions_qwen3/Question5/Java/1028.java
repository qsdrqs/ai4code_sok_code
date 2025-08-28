public class IntegerToStringWithCommas {

    public static void main(String[] args) {
        int num = 7000;
        String str = intToString(num);
        System.out.println(str); // Output: 7,000
    }

    public static String intToString(int num) {
        if (num == 0) {
            return "0";
        }

        boolean isNegative = num < 0;
        String numStr = String.valueOf(Math.abs(num));
        String reversed = new StringBuilder(numStr).reverse().toString();

        StringBuilder withCommas = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            withCommas.append(reversed.charAt(i));
            if ((i + 1) % 3 == 0 && (i + 1) != reversed.length()) {
                withCommas.append(',');
            }
        }

        String result = withCommas.reverse().toString();

        if (isNegative) {
            result = "-" + result;
        }

        return result;
    }
}