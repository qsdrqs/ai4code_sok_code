public static String encrypt(String text, String key) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
        char thisChar = (char) (text.charAt(i) + key.charAt(i % key.length()));
        result.append(thisChar);
    }
    return result.toString();
}

public static String decrypt(String text, String key) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
        char thisChar = (char) (text.charAt(i) - key.charAt(i % key.length()));
        result.append(thisChar);
    }
    return result.toString();
}