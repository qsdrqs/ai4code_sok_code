// Import the necessary traits and types from the num-format crate.
use num_format::{Locale, ToFormattedString};

// The function takes an i32, which is the Rust equivalent of a typical C int.
fn print_number(num: i32) {
    // Locale::from_env() reads the system's locale settings (e.g., from the LANG
    // environment variable). This is the direct equivalent of C's setlocale(LC_NUMERIC, "").
    let locale = Locale::from_env();

    // The to_formatted_string method formats the number according to the given locale.
    // This is the equivalent of printf's "%'" format specifier.
    let formatted_num = num.to_formatted_string(&locale);

    // Print the resulting string.
    println!("{}", formatted_num);
}

fn main() {
    // In Rust, you can use underscores as visual separators in numbers.
    // This doesn't change the value of the number.
    print_number(7_000_000);
}