public class NumberToString {

    public static void main(String[] args) {
        int num = -7000000;
        String strNum = numberToString(num);
        System.out.println(strNum);
    }

    public static String numberToString(int num) {
        StringBuilder strNum = new StringBuilder();
        int numSize = 0, temp = num;
        int commaCount = 0;

        if (num < 0) {
            temp *= -1;
        }
        while (temp > 0) {
            temp /= 10;
            numSize++;
        }
        commaCount = (numSize - 1) / 3;

        if (num < 0) {
            strNum.append('-');
            num *= -1;
        }

        for (int j = numSize - 1; j >= 0; j--) {
            strNum.append((char) ((num / power(10, j)) + '0'));
            num %= power(10, j);
            if ((j % 3 == 0) && (j != 0)) {
                strNum.append(',');
            }
        }

        return strNum.toString();
    }

    public static int power(int base, int exponent) {
        int result = 1;
        while (exponent > 0) {
            result *= base;
            exponent--;
        }
        return result;
    }
}