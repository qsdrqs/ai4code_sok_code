/// This function takes an integer and returns it as a string with thousand separators.
///
/// For example, 1000000 becomes "1,000,000" and -12345 becomes "-12,345".
fn number_to_string(num: i32) -> String {
    // Handle the edge case of 0 immediately.
    if num == 0 {
        return "0".to_string();
    }

    // Convert the absolute value of the number to a string to work with its digits.
    let s_abs = num.abs().to_string();
    let mut result = String::new();
    
    // We iterate over the digits in reverse to easily group them by threes.
    for (i, c) in s_abs.chars().rev().enumerate() {
        // A comma is needed if we have already placed at least one digit (i > 0)
        // and we are at a multiple of 3.
        if i > 0 && i % 3 == 0 {
            result.push(',');
        }
        result.push(c);
    }

    // The result is currently reversed (e.g., "000,007,1-"). We need to reverse it back.
    let mut final_result: String = result.chars().rev().collect();

    // If the original number was negative, prepend the '-' sign.
    if num < 0 {
        final_result.insert(0, '-');
    }

    final_result
}

// The `power` function from the C code is not needed in this idiomatic Rust implementation.
// If you did need it, Rust has a built-in `pow` method: `base.pow(exponent)`.

fn main() {
    // In Rust, you can use underscores in numbers for readability.
    let num = -7_000_000;
    let str_num = number_to_string(num);
    println!("{}", str_num);

    // Some other test cases to show it works
    println!("12345 -> {}", number_to_string(12345));
    println!("123 -> {}", number_to_string(123));
    println!("1000000000 -> {}", number_to_string(1_000_000_000));
    println!("0 -> {}", number_to_string(0));
}