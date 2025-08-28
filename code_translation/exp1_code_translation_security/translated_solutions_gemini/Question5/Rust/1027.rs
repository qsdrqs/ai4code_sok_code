// To run this code, you need to add these dependencies to your Cargo.toml file:
//
// [dependencies]
// num-format = "0.4.4"
// sys-locale = "0.3.1"

use num_format::{Locale, ToFormattedString};
use sys_locale::get_locale;

/// The `convert` function takes an integer and prints it to the console,
/// formatted with thousands separators according to the system's locale.
///
/// It returns 0 to match the signature of the original C function.
fn convert(i: i32) -> i32 {
    // In C, `setlocale(LC_NUMERIC, "")` gets the user's default locale from the environment.
    // In Rust, we can use the `sys-locale` crate to achieve the same thing.
    // We provide a fallback to "en" (English) in case the locale cannot be determined.
    let system_locale_name = get_locale().unwrap_or_else(|| String::from("en"));

    // The `num-format` crate uses its own `Locale` enum. We create one from the
    // name we retrieved. If the system's locale isn't supported by the crate,
    // we fall back to the default English locale.
    let locale = Locale::from_name(&system_locale_name).unwrap_or(Locale::en);

    // Format the integer `i` into a string using the determined locale.
    // This is the equivalent of `printf("%'d", i)`.
    let formatted_number = i.to_formatted_string(&locale);

    // Print the resulting string, followed by a newline.
    println!("{}", formatted_number);

    // Return 0, just like the C function.
    0
}

fn main() {
    // We use a larger number to better demonstrate the thousands separator.
    let a = 4_000_000;
    convert(a);
}