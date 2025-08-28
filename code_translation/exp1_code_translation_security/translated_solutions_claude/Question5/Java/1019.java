public class StringRepresentation {
    
    // Java equivalent of the string_rep function
    public static String stringRep(int num) {
        // In Java, we can simply use Integer.toString() or String.valueOf()
        // No need for manual memory management
        return Integer.toString(num);
    }
    
    public static void main(String[] args) {
        int leet = 1337;
        String leetStr = stringRep(leet);
        System.out.print(leetStr);
        // No need to free memory in Java - garbage collector handles it
    }
}