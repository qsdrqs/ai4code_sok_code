// Cargo.toml must contain (will be supplied for you):
// num-format = "0.4"

use num_format::{Locale, ToFormattedString};
use std::env;

/// Prints `num` with the thousands–separator that belongs to the current
/// LC_NUMERIC / LANG locale – just like the original C code that called
/// `setlocale(LC_NUMERIC, "")` and then used the “%'d” printf-specifier.
fn print_number(num: i32) {
    /* The C version’s  setlocale(LC_NUMERIC, "")  means:
     *   “Use whatever the process environment requests”.
     *
     * We mimic that by looking at LC_NUMERIC first, falling back to LANG.
     * If neither variable is set or the value is unknown to the `num-format`
     * crate, we fall back to the English locale.
     */
    let locale_name = env::var("LC_NUMERIC")
        .or_else(|_| env::var("LANG"))
        .unwrap_or_else(|_| "C".into());

    // Try to turn the environment string into a `num_format::Locale`.
    // `from_name` understands spellings such as "en_US", "de_DE", …
    // If it fails, default to English.
    let locale = Locale::from_name(&locale_name).unwrap_or(Locale::en);

    // Finally, print the number with the locale-appropriate separator.
    println!("{}", num.to_formatted_string(&locale));
}

fn main() {
    print_number(7_000_000);
}