// Using Integer.toString()
public static String inttostring(int x) {
    return Integer.toString(x);
}

// Using String concatenation
public static String inttostring(int x) {
    return "" + x;
}

// Using String.format() (closest to original sprintf approach)
public static String inttostring(int x) {
    return String.format("%d", x);
}