public class Main {
    
    // A helper function to insert comma into the string at the given index
    public static String insert(String str, int index) {
        // Use substring to copy the original string from the beginning and stop at the index parameter
        return str.substring(0, index) + "," + str.substring(index);
    }
    
    public static String stringRepre(int num) {
        String strnum = String.valueOf(num);
        
        int l = strnum.length();           // length before the insertion
        int conum = (l - 1) / 3;           // number of commas needed
        
        String buff = strnum;              // will hold the string repre along with the commas
        
        for (int i = 1; i <= conum; i++) {
            // l - 3*i is the index to be inserted (working right-to-left)
            buff = insert(buff, l - 3 * i);
        }
        return buff;
    }
    
    public static void main(String[] args) {
        String str = stringRepre(args.length);
        System.out.println(str);
    }
}