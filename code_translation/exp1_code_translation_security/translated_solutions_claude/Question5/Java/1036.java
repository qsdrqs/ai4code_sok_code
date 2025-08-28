public class NumberFormatter {
    
    public static int numDigits(int n) {
        if (n == 0) // Fixed: was assignment (=) instead of comparison (==)
            return 1;
        int cnt = 0;
        while (n != 0) {
            n = n / 10;
            cnt++;
        }
        return cnt;
    }
    
    public static String returnString(int num) {
        int digitCount = numDigits(num);
        char[] str = new char[digitCount]; // Note: str array is never populated in original C code
        char[] ret = new char[digitCount];
        
        int i = 0;
        
        while (i != (digitCount + 1)) {
            // Add a comma at every third index
            if (i % 3 == 0) {
                ret[i] = ',';
            } else {
                if (i < str.length) { // Added bounds check
                    ret[i] = str[i];
                }
            }
            System.out.print((int)ret[i]); // Prints ASCII value like original printf("%d", ret[i])
            i++;
        }
        
        return new String(ret); // Convert char array to String
    }
    
    public static void main(String[] args) {
        int num = 7000000;
        String str = returnString(num);
        System.out.print(str);
    }
}