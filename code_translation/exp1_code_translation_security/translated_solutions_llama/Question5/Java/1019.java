import java.lang.Math;

public class Main {

    /**
     * Returns a string representation of the given number.
     * 
     * @param num The number to convert to a string.
     * @return A string representation of the number.
     */
    public static String stringRep(int num) {
        // Convert the number to a string
        return Integer.toString(num);
    }

    public static void main(String[] args) {
        int leet = 1337;
        String leetStr = stringRep(leet);
        System.out.print(leetStr);
    }
}