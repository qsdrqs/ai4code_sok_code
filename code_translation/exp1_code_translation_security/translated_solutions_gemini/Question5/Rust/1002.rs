// No external dependencies are needed for this translation.
// All necessary functionality is in the Rust standard library.

/// Converts a 64-bit integer into a heap-allocated string,
/// with commas as thousands separators.
///
/// This function corrects a logic bug in the original C implementation
/// to ensure commas are placed correctly.
fn convert(value: i64) -> String {
    // Convert the number to a string. This is simpler and safer than `snprintf`.
    let s = value.to_string();

    // Handle negative numbers by separating the sign from the digits.
    // The formatting logic will be applied only to the number part.
    let (prefix, num_part) = if let Some(num) = s.strip_prefix('-') {
        ("-", num)
    } else {
        ("", s.as_str())
    };

    let len = num_part.len();
    if len <= 3 {
        // If the number part is 3 digits or less, no commas are needed.
        return s;
    }

    // Pre-allocate a new String with enough capacity for the digits and the commas.
    // This is a performance optimization to avoid reallocations.
    let mut result = String::with_capacity(s.len() + (len - 1) / 3);
    result.push_str(prefix); // Add the "-" sign back if it was there.

    // Calculate the length of the first group of digits (1, 2, or 3).
    let first_chunk_len = len % 3;
    if first_chunk_len == 0 {
        // If len is a multiple of 3 (e.g., 6), the first chunk has 3 digits.
        result.push_str(&num_part[..3]);
        // Iterate over the remaining digits in chunks of 3.
        for chunk in num_part[3..].as_bytes().chunks(3) {
            result.push(',');
            // `from_utf8_unchecked` is safe here because we know `num_part` is valid UTF-8.
            result.push_str(unsafe { std::str::from_utf8_unchecked(chunk) });
        }
    } else {
        // If len is not a multiple of 3, add the first irregular chunk.
        result.push_str(&num_part[..first_chunk_len]);
        // Iterate over the remaining digits in chunks of 3.
        for chunk in num_part[first_chunk_len..].as_bytes().chunks(3) {
            result.push(',');
            result.push_str(unsafe { std::str::from_utf8_unchecked(chunk) });
        }
    }

    result
}

fn main() {
    // Test with the original value
    let test1 = convert(1000);
    println!("1000 -> {}", test1); // Expected: 1,000

    // Test with a larger number
    let test2 = convert(1234567890);
    println!("1234567890 -> {}", test2); // Expected: 1,234,567,890

    // Test with a negative number
    let test3 = convert(-12345);
    println!("-12345 -> {}", test3); // Expected: -12,345

    // Test with a number that doesn't need commas
    let test4 = convert(100);
    println!("100 -> {}", test4); // Expected: 100

    // The `String` returned by `convert` is automatically deallocated
    // when `test1`, `test2`, etc. go out of scope. No `free()` is needed.
}