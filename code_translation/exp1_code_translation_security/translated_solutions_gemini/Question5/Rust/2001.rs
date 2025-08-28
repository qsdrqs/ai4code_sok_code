/// Formats an integer into a string with thousands separators.
/// This version manually implements the logic from the C code.
fn int_to_string_manual(a: i32) -> String {
    // Convert the integer to a string. This replaces `sprintf`.
    let s = a.to_string();

    // Determine if the number is negative and get a slice of just the digits.
    // `strip_prefix` is a safe way to handle the optional '-' sign.
    let digits_str = if let Some(stripped) = s.strip_prefix('-') {
        stripped
    } else {
        s.as_str()
    };

    let len = digits_str.len();
    // Pre-allocate a string with a reasonable capacity for efficiency.
    let mut result = String::with_capacity(len + len / 3 + 1);

    // Add the negative sign back if it was present.
    if a < 0 {
        result.push('-');
    }

    // Iterate over the digits and their indices.
    for (i, ch) in digits_str.chars().enumerate() {
        // This is the direct translation of the C code's comma-insertion logic:
        // if (buf_idx != 0 && (len - buf_idx) % 3 == 0)
        if i > 0 && (len - i) % 3 == 0 {
            result.push(',');
        }
        result.push(ch);
    }

    result
}

fn main() {
    // Note: We use the manual function here to show it works.
    // In a real project, you'd use the idiomatic version from above.
    println!("{}", int_to_string_manual(7_000_000));
    println!("{}", int_to_string_manual(700_000));
    println!("{}", int_to_string_manual(70_000));
    println!("{}", int_to_string_manual(7_000));
    println!("{}", int_to_string_manual(700));
    println!("{}", int_to_string_manual(70));
    println!("{}", int_to_string_manual(7));
    println!("{}", int_to_string_manual(0));
    println!("{}", int_to_string_manual(-7_000_000));
    println!("{}", int_to_string_manual(-700_000));
    println!("{}", int_to_string_manual(-70_000));
    println!("{}", int_to_string_manual(-7_000));
    println!("{}", int_to_string_manual(-700));
    println!("{}", int_to_string_manual(-70));
    println!("{}", int_to_string_manual(-7));
    println!("{}", int_to_string_manual(i32::MAX)); // Corresponds to INT_MAX
    println!("{}", int_to_string_manual(i32::MIN));  // Corresponds to INT_MIN
}