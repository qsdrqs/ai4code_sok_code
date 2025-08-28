// No external dependencies are needed for this direct translation.

/// Converts an integer to a string, adding commas as thousands separators.
/// This function mimics the logic of the original C code.
fn int_to_string(num: i32) -> String {
    // Handle the edge case of 0, which the original C code missed.
    if num == 0 {
        return "0".to_string();
    }

    // Handle negative numbers by converting the absolute value and adding a '-' later.
    let is_negative = num < 0;
    // Use u32 to avoid overflow with i32::MIN
    let mut temp = if is_negative {
        // Safely get absolute value of i32::MIN
        (num as i64).abs() as u64
    } else {
        num as u64
    };

    // A growable string is the Rust equivalent of the malloc'd buffer.
    // It will be built in reverse, just like in the C code.
    let mut reversed_str = String::new();
    let mut count = 0;

    // Loop to extract digits and add commas.
    while temp > 0 {
        // Get the last digit and convert it to a character.
        let digit = (temp % 10) as u8;
        reversed_str.push((b'0' + digit) as char);
        temp /= 10;
        count += 1;

        // Add a comma every three digits, but not at the very end.
        if count == 3 && temp > 0 {
            reversed_str.push(',');
            count = 0;
        }
    }

    // If the number was negative, add the sign.
    if is_negative {
        reversed_str.push('-');
    }

    // Reverse the string to get the correct order and return it.
    // The `chars().rev().collect()` is the idiomatic Rust way to reverse a string.
    reversed_str.chars().rev().collect()
}

fn main() {
    // The original C code had a bug here: printf(*int_to_string(7000000));
    // The correct C would be: printf("%s", int_to_string(7000000));
    // The Rust equivalent is using the println! macro.
    println!("{}", int_to_string(7_000_000));
    println!("{}", int_to_string(123));
    println!("{}", int_to_string(12345));
    println!("{}", int_to_string(-1234567));
    println!("{}", int_to_string(0));
}