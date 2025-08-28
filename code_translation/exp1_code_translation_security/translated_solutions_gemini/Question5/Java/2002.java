import java.text.NumberFormat;
import java.util.Locale;

/**
 * This class contains the Java equivalent of the C function sigNumToStr.
 * All dependencies are included in this single file.
 */
public class NumberConverter {

  /**
   * Translates the C function `sigNumToStr`.
   * <p>
   * This method takes an integer and formats it into a string using the
   * default locale's thousands separators. For example, in an English locale,
   * 1234567 would be formatted as "1,234,567".
   *
   * @param num The integer to be formatted.
   * @return A string representation of the number, formatted for the current locale.
   */
  public static String sigNumToStr(int num) {
    // The C code's `setlocale(LC_NUMERIC, "")` sets the numeric formatting
    // to the user's default locale. In Java, the equivalent is `Locale.getDefault()`.
    // We then get a number formatter instance for that specific locale.
    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.getDefault());

    // The `format` method converts the integer to a string, automatically
    // applying the correct grouping separators. This replaces `sprintf(text, "%'d", num)`.
    return numberFormat.format(num);
  }

  /**
   * Main method to demonstrate the functionality of sigNumToStr.
   */
  public static void main(String[] args) {
    int testNumber1 = 1234567890;
    int testNumber2 = -54321;
    int testNumber3 = 1000;

    System.out.println("Default Locale: " + Locale.getDefault().getDisplayName());
    System.out.println(testNumber1 + " -> " + sigNumToStr(testNumber1));
    System.out.println(testNumber2 + " -> " + sigNumToStr(testNumber2));
    System.out.println(testNumber3 + " -> " + sigNumToStr(testNumber3));
  }
}