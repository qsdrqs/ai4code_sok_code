// Import necessary components from the crates.
// ToFormattedString is a trait that adds the .to_formatted_string() method to numbers.
use num_format::{Locale, ToFormattedString};
// FromStr is a trait for parsing a string into a type, here for Locale.
use std::str::FromStr;

/// Formats an integer into a string with locale-specific thousands separators.
///
/// This function attempts to detect the system's locale. If successful, it formats
/// the number according to that locale. If the locale cannot be determined or is
/// not supported, it falls back to a plain, unformatted string.
///
/// # Arguments
/// * `num` - The 32-bit signed integer to format.
///
/// # Returns
/// An owned `String` containing the formatted number.
fn sig_num_to_str(num: i32) -> String {
    // 1. Attempt to get the system locale as a string (e.g., "en-US").
    //    sys_locale::get_locale() returns an Option<String>.
    if let Some(locale_name) = sys_locale::get_locale() {
        // 2. Try to parse the locale string into a `num_format::Locale`.
        //    The `from_str` method returns a Result, so we check for `Ok`.
        if let Ok(locale) = Locale::from_str(&locale_name) {
            // 3. If both steps succeed, format the number using the detected locale
            //    and return the result.
            return num.to_formatted_string(&locale);
        }
    }

    // 4. Fallback: If getting or parsing the locale fails, convert the number
    //    to a string without any special formatting.
    num.to_string()
}

fn main() {
    // Note: The output of this program depends on your system's locale settings.
    // For a system with an "en-US" locale, you'll see commas.
    // For a system with a "de-DE" locale, you'll see periods.
    let number = 123456789;
    let formatted_string = sig_num_to_str(number);
    println!(
        "Formatting {} -> \"{}\"",
        number, formatted_string
    );

    let neg_number = -9876543;
    let formatted_neg_string = sig_num_to_str(neg_number);
    println!(
        "Formatting {} -> \"{}\"",
        neg_number, formatted_neg_string
    );

    let small_number = 42;
    let formatted_small_string = sig_num_to_str(small_number);
    println!(
        "Formatting {} -> \"{}\"",
        small_number, formatted_small_string
    );
}