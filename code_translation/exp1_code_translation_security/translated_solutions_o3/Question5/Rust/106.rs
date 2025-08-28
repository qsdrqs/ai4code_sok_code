use std::iter::FromIterator;

/// Reverse the contents of a string.
///
/// The function allocates a new `String` and returns it, just like the C
/// version that returns a freshly–`malloc`ed buffer.
fn reverse_str(s: &str) -> String {
    // `chars()` iterates over Unicode scalar values; `rev()` reverses the order,
    // and `collect()` turns the iterator back into a `String`.
    s.chars().rev().collect()
}

/// Return a string representation of a signed integer.
///
/// This re-implements (fairly literally) what the C `strOfInt` does,
/// instead of simply calling `num.to_string()`, to stay close to the
/// original logic.
fn str_of_int(mut num: i32) -> String {
    // Special-case zero so the loop below doesn’t produce an empty string.
    if num == 0 {
        return "0".to_owned();
    }

    let mut buf = String::new();
    let is_negative = num < 0;

    if is_negative {
        num = -num;       // work with the absolute value
    }

    // Extract digits least-significant first,
    // converting each one to the corresponding ASCII character.
    while num != 0 {
        let digit = (num % 10) as u8;
        buf.push((digit + b'0') as char);
        num /= 10;
    }

    if is_negative {
        buf.push('-');    // append the minus sign last
    }

    // The digits (and possibly the minus sign) are in reverse order,
    // so reverse the whole string before returning it.
    reverse_str(&buf)
}

fn main() {
    println!("{}", str_of_int(7_000_000));
}