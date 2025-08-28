use std::alloc::{alloc, dealloc, Layout};
use std::ptr;
use std::ffi::CString;

/// Reverse a string
///
/// # Arguments
/// * `s` - A string slice to reverse
///
/// # Returns
/// A new `String` that is the reverse of the input string
fn reverse_str(s: &str) -> String {
    s.chars().rev().collect()
}

/// Return a string representation of a signed integer
///
/// # Arguments
/// * `num` - Input number as a signed integer
///
/// # Returns
/// A `String` representation of the signed integer
fn str_of_int(num: i32) -> String {
    let mut num = num;
    let mut is_neg = false;
    let mut result = String::new();

    if num < 0 {
        is_neg = true;
        num = -num;
    }

    loop {
        result.push((num % 10) as u8 as char);
        num /= 10;
        if num == 0 {
            break;
        }
    }

    if is_neg {
        result.push('-');
    }

    reverse_str(&result)
}

fn main() {
    let result = str_of_int(7000000);
    println!("{}", result);
}