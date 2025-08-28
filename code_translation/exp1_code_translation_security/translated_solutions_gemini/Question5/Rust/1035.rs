// In C, this function calculates 10 to the power of x.
// Note: In Rust, it's more idiomatic to use the built-in `pow()` method,
// e.g., `10i32.pow(x as u32)`. We provide a direct translation here for completeness.
fn e10(x: i32) -> i32 {
    let mut v = 1;
    // A `for` loop in Rust `0..x` would panic if x is negative.
    // The C loop simply doesn't run, so we check for x > 0.
    if x > 0 {
        for _ in 0..x {
            v *= 10;
        }
    }
    v
}

// In C, this function determines the sign of a number.
// Note: Rust has a built-in `signum()` method on numeric types (`x.signum()`).
fn signum(x: i32) -> i32 {
    if x < 0 {
        -1
    } else if x == 0 {
        0
    } else {
        1
    }
}

// In C, this function calculates the absolute value.
// Note: Rust has a built-in `abs()` method on numeric types (`x.abs()`).
fn abs(x: i32) -> i32 {
    x * signum(x)
}

/// Translates an integer to a string, adding commas as thousands separators.
///
/// This function is a safe, idiomatic Rust implementation of the C original's intent.
/// It avoids the pitfalls of the C version (like static buffers and potential overflows)
/// by returning a new, owned `String` for each call.
fn int_to_str(x: i32) -> String {
    // Handle the edge case of 0.
    if x == 0 {
        return "0".to_string();
    }

    // Work with the absolute value of the number and track the sign separately.
    let is_negative = x < 0;
    let mut num = x.abs();

    let mut result_chars: Vec<char> = Vec::new();
    let mut digit_count = 0;

    // Extract digits from right to left.
    while num > 0 {
        // Add a comma every three digits (but not at the beginning).
        if digit_count > 0 && digit_count % 3 == 0 {
            result_chars.push(',');
        }

        // Get the last digit, convert to a character, and add to our vector.
        let digit = (num % 10) as u8;
        result_chars.push((b'0' + digit) as char);
        
        num /= 10; // Move to the next digit.
        digit_count += 1;
    }

    // Add the negative sign if necessary.
    if is_negative {
        result_chars.push('-');
    }

    // The characters were added in reverse order (e.g., 12345 -> "543,21"),
    // so we reverse the vector and collect it into a String.
    result_chars.into_iter().rev().collect()
}

fn main() {
    println!("{}", int_to_str(55));
    println!("{}", int_to_str(12345));
    println!("{}", int_to_str(-55));
    println!("{}", int_to_str(-123456789));
    // An extra test case to show it handles the zero case.
    println!("{}", int_to_str(0));
    // An extra test case for a larger number.
    println!("{}", int_to_str(1_000_000));
}