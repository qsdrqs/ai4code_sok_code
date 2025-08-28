use std::iter::Peekable;

/// Converts an integer to a string with commas as thousand separators.
///
/// # Examples
/// ```
/// assert_eq!(int_to_string(7000000), "7,000,000");
/// assert_eq!(int_to_string(-7000000), "-7,000,000");
/// assert_eq!(int_to_string(0), "0");
/// ```
fn int_to_string(a: i32) -> String {
    let s = a.to_string(); // Convert the integer to a string
    let mut result = String::new(); // Result string to build the output
    let mut chars = s.chars().peekable(); // Peekable iterator over the characters

    // Check if the number is negative
    if let Some(&'-') = chars.peek() {
        result.push('-'); // Add the negative sign
        chars.next(); // Skip the '-' character
    }

    // Collect the remaining characters (digits) into a vector
    let digits: Vec<char> = chars.collect();
    let len = digits.len(); // Length of the digits

    // Insert commas every 3 digits from the right
    for (i, &c) in digits.iter().enumerate() {
        if i > 0 && (len - i) % 3 == 0 {
            result.push(','); // Insert a comma before the current digit
        }
        result.push(c); // Add the current digit
    }

    result // Return the formatted string
}

fn main() {
    // Test cases
    println!("{}", int_to_string(7000000));
    println!("{}", int_to_string(700000));
    println!("{}", int_to_string(70000));
    println!("{}", int_to_string(7000));
    println!("{}", int_to_string(700));
    println!("{}", int_to_string(70));
    println!("{}", int_to_string(7));
    println!("{}", int_to_string(0));
    println!("{}", int_to_string(-7000000));
    println!("{}", int_to_string(-700000));
    println!("{}", int_to_string(-70000));
    println!("{}", int_to_string(-7000));
    println!("{}", int_to_string(-700));
    println!("{}", int_to_string(-70));
    println!("{}", int_to_string(-7));
    println!("{}", int_to_string(i32::MAX));
    println!("{}", int_to_string(i32::MIN));
}