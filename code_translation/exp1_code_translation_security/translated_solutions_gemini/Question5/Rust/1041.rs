/*
This is a Rust translation of the C code.

The program defines a function `add_commas` that takes an unsigned integer,
converts it into a string, and adds commas for thousands separators.

It also includes a helper function `reverse_str` to reverse a string,
mimicking the structure of the C original.
*/

fn main() {
    let num: u32 = 1_000_000;
    let formatted_num = add_commas(num);
    println!("{}", formatted_num); // Prints "1,000,000"

    // In Rust, the memory for `formatted_num` (a String) is automatically
    // managed by the ownership system. It will be freed when the variable
    // goes out of scope at the end of `main`. There is no need for a
    // manual `free()` call like in C.
}

/// Reverses a string slice and returns a new, reversed String.
///
/// In C, string reversal is often done "in-place" by swapping bytes. In Rust,
/// strings are UTF-8 encoded, so swapping bytes can corrupt multi-byte characters.
/// The idiomatic and safe way is to create a new String from an iterator of
/// reversed characters.
fn reverse_str(s: &str) -> String {
    s.chars().rev().collect()
}

/// Takes an unsigned integer, converts it to a string, and adds commas.
///
/// This function implements the same logic as the C version: it builds the
/// string in reverse order while adding commas, and then reverses the final
/// result to get the correct order.
fn add_commas(num: u32) -> String {
    // The original C code has a bug for num = 0, where it would produce an
    // empty string. We handle this case explicitly for correctness.
    if num == 0 {
        return "0".to_string();
    }

    let mut num_copy = num;
    let mut reversed_s = String::new();
    let mut digit_count = 0;

    while num_copy > 0 {
        // Get the last digit and append its character representation to our string.
        // `b'0'` is a byte literal for the ASCII character '0'.
        let last_digit = (num_copy % 10) as u8;
        reversed_s.push((b'0' + last_digit) as char);
        
        num_copy /= 10;
        digit_count += 1;

        // If we've added 3 digits and there are more digits remaining, add a comma.
        if digit_count == 3 && num_copy > 0 {
            reversed_s.push(',');
            digit_count = 0; // Reset the counter for the next group of digits.
        }
    }

    // The string was built in reverse (e.g., "000,000,1"), so we use our
    // helper function to reverse it to the correct order ("1,000,000").
    reverse_str(&reversed_s)
}