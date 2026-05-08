public class Main {
    static char[] out = new char[17];
    
    static int e10(int x) {
        int v = 1;
        for (int i = 0; i < x; i++) {
            v *= 10;
        }
        return v;
    }
    
    static int signum(int x) {
        if (x < 0) return -1;
        if (x == 0) return 0;
        return 1;
    }
    
    static int abs(int x) {
        return x * signum(x);
    }
    
    static String int_to_str(int x) {
        int exp_offset = 0;
        
        for (int i = 0; i < 16; i++) {
            int digit = i - exp_offset;
            out[16 - i] = (char)(0x30 + abs((x % e10(digit + 1)) / e10(digit)));
            
            if (x % e10(digit + 1) == x) {
                if (x < 0) {
                    out[16 - (i + 1)] = '-';
                    i++;
                }
                return new String(out, 16 - i, i + 1);
            }
            
            if (i == 2 || i == 6 || i == 10) {
                out[16 - i - 1] = ',';
                i += 1;
                exp_offset += 1;
            }
        }
        return new String(out);
    }
    
    public static void main(String[] args) {
        System.out.println(int_to_str(55));
        System.out.println(int_to_str(12345));
        System.out.println(int_to_str(-55));
        System.out.println(int_to_str(-123456789));
    }
}