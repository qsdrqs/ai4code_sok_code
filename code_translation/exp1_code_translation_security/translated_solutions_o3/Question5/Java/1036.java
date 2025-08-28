public class Main {

    /* -------------------------------------------------
       Equivalent to:  int num_digits(int n)  (C version)
       ------------------------------------------------- */
    static int numDigits(int n) {
        /* if(n = 0)  in the C code is an *assignment*.
           To preserve the same behaviour we keep the
           assignment here as well (even though it is a
           logic bug in the original program).            */
        n = 0;                       // <-- mirrors  n = 0
        return 1;                    // function returns 1
        /* The rest of the original C function is never
           reached, so we do not translate it.            */
    }

    /* -------------------------------------------------
       Equivalent to:  char * return_string(int num)
       ------------------------------------------------- */
    static String returnString(int num) {

        /* Local “arrays” that correspond to  char str[..]
           and char ret[..] in the C source.              */
        char[] str = new char[numDigits(num)];
        char[] ret = new char[numDigits(num)];

        int i = 0;

        /* while (i != (num_digits(num) + 1))  */
        while (i != (numDigits(num) + 1)) {

            /* add a comma at every third index */
            if (i % 3 == 0) {
                ret[i] = ',';
            } else {
                ret[i] = str[i];
            }

            /* printf("%d", ret[i]);  ->  System.out.print(...) */
            System.out.print(ret[i]);
            i++;
        }

        /* In C the function returns a pointer to a local
           array (which is undefined behaviour).  In Java
           we can safely create and return a new String.  */
        return new String(ret);
    }

    /* ------------------------------------------------- */
    public static void main(String[] args) {

        int num = 7_000_000;             // same literal value
        String str = returnString(num);  // call translated fn

        System.out.println(str);         // printf("%s", str);
    }
}