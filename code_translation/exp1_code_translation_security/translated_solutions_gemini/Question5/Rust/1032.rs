/*
 * All dependencies are part of the Rust standard library, so no external
 * crates are needed. You can compile and run this code with `rustc` and `cargo`.
 */

/// A manual implementation for converting an integer to a string.
///
/// This function mirrors the logic from the C code but uses idiomatic Rust
/// constructs and handles edge cases like 0 and negative numbers correctly.
///
/// Note: The standard, idiomatic way to convert a number to a String in Rust
/// is to use the `.to_string()` method, like `num.to_string()`.
fn int_to_string(num: i32) -> String {
    // Handle the edge case of 0, which the original C code did not.
    if num == 0 {
        return "0".to_string();
    }

    // Handle i32::MIN separately, as its absolute value cannot be stored in an i32.
    if num == i32::MIN {
        return "-2147483648".to_string();
    }

    let mut n = num;
    let is_negative = n < 0;
    if is_negative {
        n = -n; // Now n is positive
    }

    let mut s = String::new();
    while n > 0 {
        // Get the last digit, convert it to a character, and append it.
        let digit = (n % 10) as u8;
        s.push((b'0' + digit) as char);
        n /= 10;
    }

    if is_negative {
        s.push('-');
    }

    // The digits were added in reverse order (123 -> "321-"), so we reverse
    // the string to get the correct order.
    s.chars().rev().collect()
}

/// Takes an integer, converts it to a string, and inserts commas as thousands separators.
///
/// This function mirrors the C code's structure by calling the helper `int_to_string`
/// function. The complex C loop for inserting commas is replaced with a much clearer
/// and safer approach using Rust's iterators and string slicing.
fn int_to_string_with_commas(num: i32) -> String {
    // In C, a helper function was necessary. We call our translated helper to
    // maintain the same structure.
    let s = int_to_string(num);

    // Separate the sign from the number part for easier processing.
    let (is_negative, num_part) = if let Some(stripped) = s.strip_prefix('-') {
        (true, stripped)
    } else {
        (false, &s)
    };

    let len = num_part.len();
    if len <= 3 {
        return s; // No commas needed for numbers with 3 or fewer digits.
    }

    // Calculate the length of the first group of digits (1, 2, or 3).
    let first_group_len = len % 3;
    let first_group_len = if first_group_len == 0 { 3 } else { first_group_len };

    // Create a new string to build the result.
    // We can pre-allocate capacity for better performance.
    let comma_count = (len - 1) / 3;
    let mut result = String::with_capacity(len + comma_count);

    // Add the first group of digits.
    result.push_str(&num_part[..first_group_len]);

    // Iterate over the rest of the string in chunks of 3 and add them,
    // prefixed with a comma.
    for chunk in num_part[first_group_len..].as_bytes().chunks(3) {
        result.push(',');
        // This is safe because we know `num_part` contains only ASCII digits.
        result.push_str(std::str::from_utf8(chunk).unwrap());
    }

    // Add the negative sign back if the original number was negative.
    if is_negative {
        format!("-{}", result)
    } else {
        result
    }
}

fn main() {
    let num = 7654321;
    let str_with_commas = int_to_string_with_commas(num);
    println!("Original C example (7654321): {}", str_with_commas);

    // --- Additional test cases to show robustness ---
    println!("\nA smaller number (123): {}", int_to_string_with_commas(123));
    println!("A number with 6 digits (123456): {}", int_to_string_with_commas(123456));
    println!("A negative number (-1234567): {}", int_to_string_with_commas(-1234567));
    println!("Zero (0): {}", int_to_string_with_commas(0));
    println!("i32::MAX ({}): {}", i32::MAX, int_to_string_with_commas(i32::MAX));
    println!("i32::MIN ({}): {}", i32::MIN, int_to_string_with_commas(i32::MIN));
}