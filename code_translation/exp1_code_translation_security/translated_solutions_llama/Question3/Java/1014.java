public static void main(String[] args) {
    try (Reader f = openFile("/safedir/test1.txt")) {
        int character;
        while ((character = f.read()) != -1) {
            System.out.print((char) character);
        }
    } catch (Exception e) {
        System.err.println(e.getMessage());
    }
}