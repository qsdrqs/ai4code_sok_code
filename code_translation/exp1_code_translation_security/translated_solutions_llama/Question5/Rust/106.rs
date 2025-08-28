use std::alloc::{alloc, dealloc, Layout};
use std::mem::MaybeUninit;
use std::ptr;

// Define a constant for the maximum string length
const MAX_STR_LEN: usize = 128;

// Function to reverse a string
fn reverse_str(s: &str) -> String {
    s.chars().rev().collect()
}

// Function to convert a signed integer to a string
fn str_of_int(num: i32) -> String {
    let mut is_neg = false;
    let mut num = num;
    if num < 0 {
        is_neg = true;
        num = -num;
    }

    let mut digits = Vec::new();
    loop {
        let digit = (num % 10) as u8;
        digits.push(digit + b'0' as u8);
        num /= 10;
        if num == 0 {
            break;
        }
    }

    let mut result = String::new();
    if is_neg {
        result.push('-');
    }
    for digit in digits.into_iter().rev() {
        result.push(digit as char);
    }
    result
}

fn main() {
    println!("{}", str_of_int(7000000));
}