/// Takes a signed 32-bit integer and returns it as a string
/// with thousand separators (commas).
fn int_to_string_with_commas(num: i32) -> String {
    // First, convert the number to a basic string.
    // This is much simpler than C's snprintf gymnastics.
    let s = num.to_string();

    // The logic in the C code is complex and buggy. A more robust
    // approach is to build the new string by iterating over the old one.
    // We will build it in reverse and then reverse the final result.

    let mut result_rev = String::new();
    let mut char_count = 0;

    // Iterate over the characters of the number string in reverse.
    // We skip the '-' sign for now if it exists.
    for c in s.chars().rev() {
        if c == '-' {
            continue;
        }

        // After every 3rd digit, insert a comma.
        // We check char_count > 0 to avoid adding a comma at the very beginning.
        if char_count > 0 && char_count % 3 == 0 {
            result_rev.push(',');
        }
        result_rev.push(c);
        char_count += 1;
    }

    // The result is currently reversed (e.g., "765,432,1").
    // Now, we reverse it back to get the correct order.
    let mut final_result = result_rev.chars().rev().collect::<String>();

    // If the original number was negative, prepend the '-' sign.
    if num < 0 {
        final_result.insert(0, '-');
    }

    final_result
}

// Main function to demonstrate the usage.
fn main() {
    let num1 = 1234567;
    let num2 = -98765;
    let num3 = 123;
    let num4 = 1234567890;
    let num5 = 0;

    println!("{} -> {}", num1, int_to_string_with_commas(num1));
    println!("{} -> {}", num2, int_to_string_with_commas(num2));
    println!("{} -> {}", num3, int_to_string_with_commas(num3));
    println!("{} -> {}", num4, int_to_string_with_commas(num4));
    println!("{} -> {}", num5, int_to_string_with_commas(num5));
}