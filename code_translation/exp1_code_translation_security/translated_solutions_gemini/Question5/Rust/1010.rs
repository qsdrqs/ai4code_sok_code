fn main() {
    // The Rust equivalent of printf is the println! macro.
    // The format specifier {} will use the Display trait for the argument.
    println!("   {}", string_it(-123457));
    println!("   {}", string_it(1234567890));
    println!("   {}", string_it(123));
    println!("   {}", string_it(-999));
}

/// Returns a String containing the expansion of the signed integer with commas.
fn string_it(value: i32) -> String {
    // First, convert the number to a basic string.
    // This handles the negative sign automatically.
    let s = value.to_string();

    // Separate the sign (if it exists) from the number part.
    let (is_negative, num_part) = if let Some(stripped) = s.strip_prefix('-') {
        (true, stripped)
    } else {
        (false, &s)
    };

    let len = num_part.len();
    if len <= 3 {
        // If the number is short, no commas are needed.
        // Just return the original string.
        return s;
    }

    // Create a new String to build the result. We can pre-allocate
    // memory for efficiency. The final length will be the original
    // length plus the number of commas.
    let comma_count = (len - 1) / 3;
    let mut result = String::with_capacity(len + comma_count + if is_negative { 1 } else { 0 });

    // If the number was negative, add the sign to our result first.
    if is_negative {
        result.push('-');
    }

    // Figure out the length of the first group of digits (1 to 3).
    let first_chunk_len = len % 3;
    if first_chunk_len == 0 {
        // If length is a multiple of 3 (e.g., 123,456), the first chunk has 3 digits.
        result.push_str(&num_part[0..3]);
    } else {
        // Otherwise, it's the remainder (e.g., for 1,234,567, the first chunk is just "1").
        result.push_str(&num_part[0..first_chunk_len]);
    }

    // Now, iterate through the rest of the number string in chunks of 3,
    // adding a comma before each chunk.
    for chunk in num_part[first_chunk_len..].as_bytes().chunks(3) {
        result.push(',');
        // chunk is a &[u8], so we convert it back to a &str to append it.
        // This is safe because we know the source is valid UTF-8.
        result.push_str(std::str::from_utf8(chunk).unwrap());
    }

    result
}