/*Write a function in Rust that takes an integer and converts it to a string*/

/// Converts an integer to a string, adding commas for thousands separators.
///
/// This function translates the *intent* of the original C code while fixing its bugs
/// (e.g., handling of 0, buffer overflows with negative numbers) and using
/// idiomatic Rust practices for safety and clarity.
///
/// # Arguments
///
/// * `num` - The 32-bit integer to convert.
///
/// # Returns
///
/// A `String` representation of the number with comma separators.
fn int_to_string(num: i32) -> String {
    // Handle the zero case, which the original C code missed.
    if num == 0 {
        return "0".to_string();
    }

    // Work with the absolute value of the number and remember the sign.
    let is_negative = num < 0;
    // Using .to_string() is the idiomatic Rust way to get a number's string representation.
    let num_str = num.abs().to_string();
    let len = num_str.len();

    // Calculate the capacity for the new String to avoid reallocations.
    // Capacity = sign (if any) + original length + number of commas.
    let num_commas = (len - 1) / 3;
    let mut result = String::with_capacity(if is_negative { 1 } else { 0 } + len + num_commas);

    // Prepend the negative sign to the result string if necessary.
    if is_negative {
        result.push('-');
    }

    // Determine the length of the first group of digits (before the first comma).
    // This will be 1, 2, or 3.
    let mut first_group_len = len % 3;
    if first_group_len == 0 {
        first_group_len = 3;
    }

    // Append the first group of digits.
    result.push_str(&num_str[..first_group_len]);

    // Append the rest of the groups, each preceded by a comma.
    let mut current_pos = first_group_len;
    while current_pos < len {
        result.push(',');
        result.push_str(&num_str[current_pos..current_pos + 3]);
        current_pos += 3;
    }

    result
}

fn main() {
    // The original C code used this number.
    // We use underscores for readability, a nice feature of Rust.
    let num = -10_099_870;
    let s = int_to_string(num);

    // `println!` is the standard, type-safe way to print to the console in Rust.
    println!("{}", s);

    // In Rust, memory management is automatic and safe. The `String` 's' is owned by the
    // `main` function, and its memory is automatically freed when it goes out of scope
    // at the end of the function. There is no need for a manual `free(s)` call.
}