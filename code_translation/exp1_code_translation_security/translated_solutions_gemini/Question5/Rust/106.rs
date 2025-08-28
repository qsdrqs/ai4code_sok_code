// The C code includes <stdio.h> for I/O. In Rust, the `print!` and `println!`
// macros from the standard library are used for this and are available by default.
// The C code also implicitly uses <string.h> for strlen() and <stdlib.h> for malloc().
// In Rust, string handling and memory management are core language features, and
// the standard library provides safe, efficient alternatives like the `String` type.

/// Translation of the `#define MAX_STR_LEN 128`.
/// While not used in this idiomatic Rust implementation, it's included for
/// faithfulness to the original C code's structure.
const MAX_STR_LEN: usize = 128;

/// Reverses a given string slice and returns a new, owned `String`.
///
/// This is the Rust equivalent of the C `reverseStr` function. The C version
/// manually allocates memory for the new string, whereas Rust's `String` type
/// handles memory management automatically.
fn reverse_str(s: &str) -> String {
    s.chars().rev().collect()
}

/// Returns a string representation of a signed integer.
///
/// This function translates the logic of the C `strOfInt` function.
///
/// # Args
/// * `num`: The input number as a signed integer (`i32`).
///
/// # Returns
/// A `String` representation of the signed integer.
fn str_of_int(num: i32) -> String {
    // The original C code has several issues that are addressed in this Rust translation:
    // 1. Memory Leak: The C `strOfInt` allocates a buffer for the reversed string,
    //    then `reverseStr` allocates *another* buffer for the final result. The first
    //    buffer is never freed. Rust's ownership system prevents such leaks automatically.
    // 2. Undefined Behavior: For `num = INT_MIN`, the expression `-num` is undefined
    //    in C. This translation handles that edge case explicitly.
    // 3. Fixed-Size Buffer: The C code uses a fixed-size buffer, which is a potential
    //    security risk (buffer overflow). Rust's `String` grows dynamically, avoiding this.

    // Handle the edge case of 0, as the loop below would not execute for it.
    if num == 0 {
        return "0".to_string();
    }

    // Handle the edge case of i32::MIN, as its absolute value cannot be stored in an i32.
    if num == i32::MIN {
        return "-2147483648".to_string();
    }

    let mut n = num;
    let is_neg = if n < 0 {
        n = -n; // This is now safe due to the i32::MIN check above.
        true
    } else {
        false
    };

    let mut reversed_str = String::new();

    // This loop builds the string representation in reverse, similar to the C `do-while` loop.
    while n > 0 {
        // Get the last digit and convert it to its ASCII character representation.
        let digit = (n % 10) as u8;
        reversed_str.push((b'0' + digit) as char);
        n /= 10;
    }

    if is_neg {
        reversed_str.push('-');
    }

    // Call the reverse function, just as the C code does.
    // The temporary `reversed_str` is passed as a borrowed slice `&str` and will be
    // automatically deallocated when this function returns, preventing a memory leak.
    reverse_str(&reversed_str)
}

fn main() {
    // The C code has a stray semicolon `main(){;` which is a null statement.
    // It has no effect and is not needed in Rust.

    // The C code calls `printf` twice. We can achieve the same result more
    // concisely with a single call to the `println!` macro, which prints
    // the formatted string followed by a newline.
    println!("{}", str_of_int(7000000));
}