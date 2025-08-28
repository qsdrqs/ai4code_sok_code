/// Formats an integer with thousands separators (e.g., 7042 -> "7,042").
///
/// This function translates the *intent* of the original C code. The C
/// implementation had bugs that prevented it from working correctly. This Rust
/// version correctly implements the formatting logic.
///
/// In idiomatic Rust, instead of modifying a pre-allocated buffer passed as a
/// parameter (an "out parameter" like `char *str`), functions typically return
/// a new owned value, like a `String`. This approach is safer and often clearer.
fn num_to_str(num: i32) -> String {
    // First, convert the number to a basic string.
    let s = num.to_string();

    // The logic for inserting commas only applies to the digits.
    // We need to handle the '-' sign separately for negative numbers.
    let is_negative = num < 0;
    
    // Get a slice of just the digits, ignoring the '-' sign if present.
    let digits_str = if is_negative { &s[1..] } else { &s[..] };
    let num_digits = digits_str.len();

    // If there are 3 or fewer digits, no commas are needed, so we can return the original string.
    if num_digits <= 3 {
        return s;
    }

    // Calculate how many commas we'll need to insert.
    let num_commas = (num_digits - 1) / 3;
    
    // Pre-allocate a new string with the correct final capacity for efficiency.
    let mut result = String::with_capacity(s.len() + num_commas);

    // If the number was negative, add the '-' sign to the result first.
    if is_negative {
        result.push('-');
    }

    // Determine the length of the first group of digits (from 1 to 3).
    // For 1234567, num_digits is 7. 7 % 3 is 1. The first group is "1".
    // For 123456, num_digits is 6. 6 % 3 is 0. The first group is "123".
    let mut first_group_len = num_digits % 3;
    if first_group_len == 0 {
        first_group_len = 3;
    }

    // Add the first group of digits to the result.
    result.push_str(&digits_str[..first_group_len]);

    // Iterate through the remaining digits and add them in groups of 3,
    // each preceded by a comma.
    let mut current_pos = first_group_len;
    while current_pos < num_digits {
        result.push(',');
        result.push_str(&digits_str[current_pos..current_pos + 3]);
        current_pos += 3;
    }

    result
}

fn main() {
    let num = 7042;
    
    // In C, you would declare a buffer: char str[24];
    // In Rust, the function returns an owned String, so we just assign the result to a new variable.
    let s = num_to_str(num);
    
    println!("Output: {}", s);

    // --- Bonus: Testing with other numbers to show robustness ---
    println!("-1234567 -> {}", num_to_str(-1234567));
    println!("123456 -> {}", num_to_str(123456));
    println!("999 -> {}", num_to_str(999));
    println!("0 -> {}", num_to_str(0));
}