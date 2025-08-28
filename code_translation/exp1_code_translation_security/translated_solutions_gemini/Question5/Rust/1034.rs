// Import necessary items from crates.
// `rand::Rng` is a trait that provides random number generation methods.
// `num_format` provides tools for formatting numbers with separators.
use rand::Rng;
use num_format::{Locale, ToFormattedString};

fn main() {
    // 1. Generate a random number.
    // `rand::thread_rng()` gets a random number generator local to the current thread.
    // In C, `long` is often 64-bit on modern systems, so we use `i64`.
    let mut rng = rand::thread_rng();
    let value: i64 = rng.gen();

    // 2. Print the original value.
    // The `println!` macro is Rust's type-safe and convenient way to print to the console.
    // Note the aligned output for better readability.
    println!("Before: \t{}", value);

    // 3. Convert the number to a string with comma separators.
    // The `num-format` crate makes this a single, clear function call.
    // We specify the `Locale::en` for US/UK-style comma separators.
    // This is far safer and simpler than the manual C loop.
    let formatted_string = value.to_formatted_string(&Locale::en);

    // 4. Print the formatted string.
    println!("With:   \t{}", formatted_string);
}