/*  File: Main.java  */

public class Main {

    // Equivalent to: char convert (int num)
    // In Java we return the first character of the string—this is the
    // closest analogue to the C code, which (erroneously) tried to
    // return a char but really returned a char*.
    public static char convert(int num) {

        /* char buf[100];               →  StringBuilder/String */
        /* strcpy(buf,"");              →  Start with empty string */
        /* sprintf(buf,"%d", num);      →  Integer.toString(num)  */
        /* printf("%s",buf);            →  System.out.print(...)  */

        String buf = Integer.toString(num);
        System.out.print(buf);

        /* return buf;   (illegal in C, but the intention seems to be
                          “return first char”)                       */
        return buf.charAt(0);
    }

    public static void main(String[] args) {

        /* printf("%c",convert(100)); */
        System.out.print(convert(100));

        // If you want a newline at the end (like typical C console
        // programs), uncomment the next line:
        // System.out.println();
    }
}