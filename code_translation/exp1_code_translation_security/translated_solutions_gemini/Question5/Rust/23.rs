/// Formats an integer into a string with thousands separators (commas).
///
/// This function correctly handles both positive and negative integers.
///
/// # Arguments
///
/// * `n` - An i32 integer to be formatted.
///
/// # Returns
///
/// A `String` containing the formatted number.
///
/// # Examples
///
/// ```
/// assert_eq!(int_to_string_with_commas(100000), "100,000");
/// assert_eq!(int_to_string_with_commas(-1234567), "-1,234,567");
/// assert_eq!(int_to_string_with_commas(123), "123");
/// ```
fn int_to_string_with_commas(n: i32) -> String {
    // First, convert the integer to a basic string.
    let s = n.to_string();

    // Create a new, empty string to build our result.
    let mut result = String::new();
    
    // We will iterate backwards through the original string's characters.
    // This makes the comma logic much simpler.
    let mut char_count = 0;
    for ch in s.chars().rev() {
        // The '-' sign is a special case; it should not have a comma before it.
        if ch == '-' {
            // Since we are iterating in reverse, the '-' will be the last character we see.
            // We push it to our result and are done.
            result.push(ch);
            break;
        }

        // If we have already placed 3 digits, it's time for a comma.
        if char_count > 0 && char_count % 3 == 0 {
            result.push(',');
        }

        // Push the current digit to our result string.
        result.push(ch);
        char_count += 1;
    }

    // Because we built the string in reverse ("000,001"),
    // we need to reverse it back to get the correct final order ("100,000").
    // The `collect()` method can build a new String from an iterator of characters.
    result.chars().rev().collect()
}

fn main() {
    // Call the function with the example number and print the result.
    // The `println!` macro is Rust's equivalent of `printf`.
    println!("{}", int_to_string_with_commas(100000));
    println!("{}", int_to_string_with_commas(123456789));
    println!("{}", int_to_string_with_commas(-54321));
    println!("test");
}