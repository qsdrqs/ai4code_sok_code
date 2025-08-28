// Rust translation of the supplied C example.
//
// ──────────────────────────────────────────────────────────────
// Cargo.toml will already contain the two crates used below
// so you don’t have to add them yourself:
//
// [dependencies]
// locale_config = "0.3"   # Read the user / system locale
// num-format     = "0.4"   # Add thousands-grouping separators
// ──────────────────────────────────────────────────────────────

use locale_config::Locale as SystemLocale;
use num_format::{Locale, ToFormattedString};

/// Mimics the C function `int convert(int i);`
fn convert(i: i32) -> i32 {
    // Obtain the user / system locale (e.g. "en_US", "de_DE", …).
    let sys_locale = SystemLocale::current();

    // Convert that string into a `num_format::Locale`.
    // If the locale string is not recognised, fall back to `Locale::en`.
    let nf_locale = Locale::from_name(&sys_locale.to_string()).unwrap_or(Locale::en);

    // Print the value with locale-aware thousands separators.
    println!("{}", i.to_formatted_string(&nf_locale));

    // Keep the return signature identical to the C version.
    0
}

fn main() {
    let a: i32 = 400;
    convert(a);
}