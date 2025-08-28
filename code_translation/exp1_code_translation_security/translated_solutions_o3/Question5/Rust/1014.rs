/*
 A function that takes a signed integer `num` and returns a `String`
 representation of the integer.  
 The logic mirrors the original C implementation: we extract the digits
 back-to-front, optionally record the minus sign, then reverse the buffer.
*/

/// Reverse a mutable slice of bytes in-place (equivalent to the C `strrev`).
fn str_rev(buf: &mut [u8]) {
    let mut start = 0;
    if buf.is_empty() {
        return;
    }
    let mut end = buf.len() - 1;

    while end > start {
        buf.swap(start, end);
        start += 1;
        // avoid under-flow when `end` is 0
        if end == 0 {
            break;
        }
        end -= 1;
    }
}

/// Convert an `i32` to its string representation without using the
/// standard `to_string()` helper, following the algorithm in the C code.
fn integer_to_string(num: i32) -> String {
    // A temporary buffer that will contain the digits in reverse order.
    // 12 bytes is enough for any 32-bit signed integer (10 digits + sign + '\0').
    let mut buf: Vec<u8> = Vec::with_capacity(12);

    // Handle sign.
    let mut n = num;
    let mut is_negative = false;
    if n < 0 {
        is_negative = true;
        n = -n;
    }

    // Extract digits (at least once, so 0 is handled).
    loop {
        let digit = (n % 10) as u8;
        buf.push(b'0' + digit);
        n /= 10;
        if n == 0 {
            break;
        }
    }

    // Add minus sign if needed.
    if is_negative {
        buf.push(b'-');
    }

    // Reverse to obtain the correct order.
    str_rev(&mut buf);

    // Convert the byte buffer into a `String`.
    // All bytes are valid UTF-8, so `unwrap` is safe.
    String::from_utf8(buf).unwrap()
}

fn main() {
    let num1 = integer_to_string(12345);
    println!("{}", num1);      // 12345

    let num2 = integer_to_string(-12345);
    println!("{}", num2);      // -12345
}