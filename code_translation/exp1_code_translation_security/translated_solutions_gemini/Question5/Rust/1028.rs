/// Takes a signed integer and returns a string representation with thousands separators.
/// For example, an input of 7000000 results in the string "7,000,000".
///
/// # Arguments
///
/// * `num` - A 32-bit signed integer to be formatted.
///
/// # Returns
///
/// A heap-allocated `String` containing the formatted number.
fn format_integer_with_commas(num: i32) -> String {
    // First, convert the integer to a standard String.
    // The `to_string()` method conveniently handles the sign.
    let s = num.to_string();

    // Separate the sign (if it exists) from the number part.
    // This makes the comma insertion logic simpler as we only need to work with digits.
    let (is_negative, num_part) = if let Some(stripped) = s.strip_prefix('-') {
        (true, stripped)
    } else {
        (false, s.as_str())
    };

    let len = num_part.len();

    // If the number part has 3 or fewer digits, no commas are needed.
    // We can return the original string, which already includes the sign if present.
    if len <= 3 {
        return s;
    }

    // Pre-allocating capacity for the new String is a performance optimization.
    // The final length will be the original length plus the number of commas needed.
    let mut result = String::with_capacity(s.len() + (len - 1) / 3);

    // Calculate how many digits are in the first group (before the first comma).
    // For 7,000,000 (len=7), the first group is "7" (len=1). `7 % 3` is 1.
    // For 123,456 (len=6), the first group is "123" (len=3). `6 % 3` is 0, so we treat it as 3.
    let mut first_group_len = len % 3;
    if first_group_len == 0 {
        first_group_len = 3;
    }

    // If the number was negative, add the sign to our result first.
    if is_negative {
        result.push('-');
    }

    // Append the first group of digits to the result.
    result.push_str(&num_part[..first_group_len]);

    // Iterate over the rest of the number string in chunks of 3.
    // The `chunks()` method on a byte slice is perfect for this.
    for chunk in num_part[first_group_len..].as_bytes().chunks(3) {
        // For each subsequent chunk, first add a comma.
        result.push(',');
        // Then, add the 3-digit chunk.
        // `chunk` is a `&[u8]`, so we must convert it back to `&str`.
        // This conversion is safe because we started from a valid UTF-8 string.
        result.push_str(std::str::from_utf8(chunk).unwrap());
    }

    result
}

/// The main function to demonstrate the usage of `format_integer_with_commas`.
fn main() {
    let num1 = 7000000;
    let formatted_str1 = format_integer_with_commas(num1);
    println!("C equivalent for 7000000: {}", formatted_str1);

    let num2 = -123456;
    let formatted_str2 = format_integer_with_commas(num2);
    println!("Test with negative number -123456: {}", formatted_str2);

    let num3 = 999;
    let formatted_str3 = format_integer_with_commas(num3);
    println!("Test with short number 999: {}", formatted_str3);
    
    let num4 = -1000;
    let formatted_str4 = format_integer_with_commas(num4);
    println!("Test with boundary number -1000: {}", formatted_str4);

    let num5 = 0;
    let formatted_str5 = format_integer_with_commas(num5);
    println!("Test with zero: {}", formatted_str5);

    let num6 = 1234567890;
    let formatted_str6 = format_integer_with_commas(num6);
    println!("Test with large number 1234567890: {}", formatted_str6);
}