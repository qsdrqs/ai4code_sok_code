/// Translates an integer into a string with comma separators for thousands.
///
/// # Arguments
///
/// * `num` - The integer to convert.
///
/// # Returns
///
/// An owned `String` representing the number, with commas.
/// e.g., -5305000 -> "-5,305,000"
fn num_to_string(num: i32) -> String {
    // Handle the simple case of zero.
    if num == 0 {
        return "0".to_string();
    }

    // Determine the sign and work with the absolute value of the number.
    let is_neg = num < 0;
    // Using .abs() is safer than `num = -num` which is undefined behavior for i32::MIN in C.
    let mut n = num.abs();

    // This string will be built in reverse order.
    // e.g., for 12345, it will become "543,21"
    let mut reversed_str = String::new();
    let mut digit_count = 0;

    while n > 0 {
        // After the first digit, insert a comma every three digits.
        if digit_count > 0 && digit_count % 3 == 0 {
            reversed_str.push(',');
        }

        // Get the last digit, convert it to a character, and push it to our string.
        // (n % 10) gets the last digit (e.g., 123 -> 3).
        // `b'0'` is the ASCII value for the character '0'.
        let digit_char = ((n % 10) as u8 + b'0') as char;
        reversed_str.push(digit_char);

        // Prepare for the next iteration.
        n /= 10; // Remove the last digit (e.g., 123 -> 12).
        digit_count += 1;
    }

    // If the original number was negative, add the '-' sign.
    // Since we built the string in reverse, this goes at the end for now.
    if is_neg {
        reversed_str.push('-');
    }

    // The string is currently backwards (e.g., "000,503,5-").
    // Reverse the characters to get the final, correct string.
    let final_str: String = reversed_str.chars().rev().collect();

    final_str
}

fn main() {
    // Use Rust's numeric separator `_` for better readability.
    let number = -5_305_000;
    let number_str = num_to_string(number);
    
    // `println!` is Rust's equivalent of `printf`.
    // The `{}` automatically formats the string for display.
    println!("{}", number_str); // Expected output: -5,305,000
    
    // Other test cases
    println!("{}", num_to_string(12345));      // Expected output: 12,345
    println!("{}", num_to_string(1234567));    // Expected output: 1,234,567
    println!("{}", num_to_string(100));        // Expected output: 100
    println!("{}", num_to_string(0));          // Expected output: 0
    println!("{}", num_to_string(-999));       // Expected output: -999
}