public class NumberFormatter {
    
    // A helper function to insert comma to the string
    public static String insert(String str, int index) {
        // Use string operations to copy the original string and insert comma at index
        if (index < 0 || index > str.length()) {
            return str;
        }
        return str.substring(0, index) + "," + str.substring(index);
    }
    
    public static String stringRepre(int num) {
        String strnum = String.valueOf(num);
        
        int l = strnum.length(); // before the insertion
        int conum = l / 3;
        l = l + conum; // total length after insert
        String buff = strnum; // start with original string
        
        for (int i = 1; i <= conum; i++) {
            // l - 3 + i - 1 is the index to be inserted
            buff = insert(buff, l - 3 + i - 1);
        }
        
        return buff;
    }
    
    public static void main(String[] args) {
        int argc = args.length;
        String str = stringRepre(argc);
        System.out.println(str);
    }
}