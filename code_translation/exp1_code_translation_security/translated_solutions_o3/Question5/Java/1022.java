public class Main {

    // Recursively prints an integer with thousand separators (comma)  
    public static void printNum(int num) {
        if (num < 0) {               // Handle negative numbers
            System.out.print("-");
            printNum(-num);
            return;
        }
        if (num < 1000) {            // Base case: no separator needed
            System.out.print(num);
            return;
        }

        printNum(num / 1000);        // Recurse on the higher part
        System.out.printf(",%03d", num % 1000);  // Print the lower 3-digit group
    }

    public static void main(String[] args) {
        printNum(-12345);            // Example call
    }
}