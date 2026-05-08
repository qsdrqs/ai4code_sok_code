public class Main {
    public static String[] stringRep(int num) {
        // heap allocate str (array size based on log10)
        int size = (int) Math.log10(num);
        String[] str = new String[size];
        
        // put number in string
        str[0] = String.format("%d", num);
        
        return str;
    }
    
    public static void main(String[] args) {
        int leet = 1337;
        String[] leetStr = stringRep(leet);
        System.out.printf("%s", leetStr[0]);
        // No explicit free needed — Java is garbage collected
    }
}