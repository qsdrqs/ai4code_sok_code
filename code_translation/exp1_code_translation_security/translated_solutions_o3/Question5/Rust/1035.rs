use std::cmp::Ordering;

/// 10ⁿ  (identical to the C helper `e10`)
fn e10(exp: u32) -> i32 {
    10_i32.pow(exp)
}

/// Replacement for the C `signum`
fn signum(x: i32) -> i32 {
    match x.cmp(&0) {
        Ordering::Less    => -1,
        Ordering::Equal   =>  0,
        Ordering::Greater =>  1,
    }
}

/// Absolute value using the above `signum` (like the C `abs`)
fn abs_val(x: i32) -> i32 {
    x * signum(x)
}

/// Equivalent of the C `int_to_str`, including the same comma placement.
///
/// It builds the result in a fixed-size array (just like the static buffer
/// in the C code) and then converts the relevant tail of that array to a
/// `String`, which is what we return in Rust.
fn int_to_str(x: i32) -> String {
    // buffer big enough for “-?,???...,???\0”  (15 visible chars + '\0' in C)
    let mut out = [0u8; 16];

    let mut exp_offset = 0_i32;   // how many commas have already been inserted
    let mut i          = 0usize;  // position we are currently writing

    while i < 16 {
        let digit             = i as i32 - exp_offset;
        let digit_value       = abs_val((x % e10((digit + 1) as u32))
                                        / e10(digit as u32));
        out[15 - i]           = 0x30 + digit_value as u8;          // ASCII digit

        // Have we just written the most significant digit?
        if x % e10((digit + 1) as u32) == x {
            if x < 0 {
                out[14 - i] = b'-';   // prepend the minus sign
                i += 1;
            }
            // slice the useful part of the buffer, turn into String and return
            let start = 16 - i;
            return String::from_utf8(out[start..].to_vec()).unwrap();
        }

        // Insert commas after 3, 7, 11 least-significant characters
        if i == 2 || i == 6 || i == 10 {
            out[14 - i] = b',';   // place the comma
            i          += 1;
            exp_offset += 1;
        }

        i += 1;
    }

    // In practice we always `return` inside the loop, but Rust needs a value.
    String::from_utf8(out.to_vec()).unwrap()
}

fn main() {
    println!("{}", int_to_str(55));
    println!("{}", int_to_str(12345));
    println!("{}", int_to_str(-55));
    println!("{}", int_to_str(-123456789));
}