/// Function: repr
///
/// Takes in a signed 32-bit integer and returns the string representation
/// of that integer with commas as thousands separators.
fn repr(n: i32) -> String {
    // Handle the special case for i32::MIN, as -n would overflow.
    if n == i32::MIN {
        return String::from("-2,147,483,648");
    }

    if n == 0 {
        return String::from("0");
    }

    let is_negative = n < 0;
    // Use .abs() which is safe now that we've handled i32::MIN
    let mut num = n.abs();

    // Create a temporary string to build the number in reverse.
    // Pre-allocating capacity is a small optimization similar to the C version's MAX_DIGITS.
    let mut reversed_str = String::with_capacity(15);
    let mut digit_count = 0;

    while num > 0 {
        // Insert a comma after every 3 digits.
        // This is done before pushing the digit to simplify the logic.
        if digit_count > 0 && digit_count % 3 == 0 {
            reversed_str.push(',');
        }

        // Get the last digit and push it as a character.
        let digit = (num % 10) as u8;
        reversed_str.push((b'0' + digit) as char);
        
        num /= 10;
        digit_count += 1;
    }

    if is_negative {
        reversed_str.push('-');
    }

    // Reverse the string to get the final correct representation.
    // This single line replaces the entire `reverse` function from the C code.
    reversed_str.chars().rev().collect()
}

/*
Note on the `reverse` function:

In C, manipulating a character array in-place is common. The provided C code
included a `reverse` function for this purpose.

In Rust, strings are UTF-8 encoded and cannot be easily modified in-place by index.
The idiomatic way to reverse a string is to use iterators, as shown above:
`my_string.chars().rev().collect()`. This is both safer and more readable,
so a separate `reverse` function is not needed.
*/

fn main() {
    // Demonstrate the function with various inputs
    let numbers_to_test = [
        0,
        123,
        1234,
        -12345,
        1234567,
        -999999,
        1000, // This case was buggy in the original C code
        i32::MAX,
        i32::MIN,
    ];

    for &n in &numbers_to_test {
        let s = repr(n);
        // The println! macro is type-safe and prevents format string vulnerabilities
        // that were possible with the `printf(str)` call in the C code.
        println!("repr({}) = {}", n, s);
    }
}