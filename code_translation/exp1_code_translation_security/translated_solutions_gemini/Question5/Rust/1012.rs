// Import the necessary traits from the num-format crate and standard library.
use num_format::{Locale, ToFormattedString};
use std::env;

/// Formats an integer with thousands separators (e.g., 1234567 -> "1,234,567").
/// This function is the safe and correct Rust equivalent of the C `stringRepre` function.
///
/// # Arguments
/// * `num` - An integer to be formatted.
///
/// # Returns
/// A `String` containing the formatted number.
fn format_with_commas(num: i64) -> String {
    // The num-format crate provides a simple and robust way to format numbers.
    // We use the English/US locale (`Locale::en`) for comma separators.
    // The `to_formatted_string` method handles all the logic for us.
    num.to_formatted_string(&Locale::en)
}

fn main() {
    // In Rust, we get command-line arguments using `std::env::args()`.
    // We collect them into a Vec<String>. The first element is the program path.
    let args: Vec<String> = env::args().collect();

    // The C code used `argc` (argument count) as the number to format.
    // A more useful example is to take the number from the command-line arguments.
    // We'll use a default value if no argument is provided.
    let number_to_format: i64 = if args.len() > 1 {
        // Try to parse the second argument (index 1) as a 64-bit integer.
        // If parsing fails (e.g., user enters "hello"), panic with a helpful message.
        args[1].parse().expect("Please provide a valid integer as a command-line argument.")
    } else {
        // Default value if no arguments are given.
        1234567890
    };

    println!("Original number: {}", number_to_format);

    // Call our formatting function.
    let formatted_string = format_with_commas(number_to_format);

    // Print the result. In Rust, `main` doesn't return a string.
    // We print output to the console.
    println!("Formatted string: {}", formatted_string);

    // Example with a negative number
    let negative_number = -9876543;
    println!("\nOriginal number: {}", negative_number);
    println!("Formatted string: {}", format_with_commas(negative_number));
}