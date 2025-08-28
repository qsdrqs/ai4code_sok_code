/// Formats an integer into a string with comma separators for thousands.
///
/// This function takes an integer, converts it to a string, and inserts
/// commas every three digits from the right.
///
/// # Arguments
///
/// * `input` - A 64-bit signed integer to be formatted.
///
/// # Returns
///
/// A `String` containing the formatted number.
fn change(mut input: i64) -> String {
    // Handle the edge case of 0 separately.
    if input == 0 {
        return "0".to_string();
    }

    // Using a String as a growable buffer is the idiomatic Rust way.
    // This avoids manual memory management (malloc/free) and potential buffer overflows.
    let mut result = String::new();
    let mut count = 0;

    // Process the number from right to left (least significant digit first).
    while input > 0 {
        // Before adding a digit, check if we need a comma.
        // Add a comma after every 3 digits, but not at the very beginning.
        if count > 0 && count % 3 == 0 {
            result.push(',');
        }

        // Get the last digit (e.g., 123 % 10 -> 3)
        let digit = (input % 10) as u8;
        // Convert the digit to a character and push it to our string.
        // e.g., 3 -> '3'
        result.push((b'0' + digit) as char);

        // Remove the last digit from the input (e.g., 123 / 10 -> 12)
        input /= 10;
        count += 1;
    }

    // The string was built in reverse order (e.g., "000,000,007").
    // We need to reverse it to get the correct final string.
    // The `chars().rev().collect()` pattern is a common Rust idiom for this.
    result.chars().rev().collect()
}

fn main() {
    // In Rust, we can use underscores in number literals for readability.
    let snum = change(700_000_000);

    // `println!` is Rust's macro for printing formatted text to the console.
    println!("{}", snum);
}