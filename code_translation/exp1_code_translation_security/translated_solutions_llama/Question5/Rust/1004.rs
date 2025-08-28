use std::alloc::{alloc, dealloc, Layout};
use std::mem::MaybeUninit;
use std::ptr;

// Define a constant for maximum digits
const MAX_DIGITS: usize = 100;

// Function to reverse a string
fn reverse(s: &mut [u8]) {
    let mut i = 0;
    let mut j = s.len() - 1;
    while i < j {
        s.swap(i, j);
        i += 1;
        j -= 1;
    }
}

// Function to get the string representation of an integer
fn repr(n: i32) -> String {
    let mut sign = 1;
    let mut num = n;

    // Handle the special case of INT_MIN
    if num == i32::MIN {
        return "-2,147,483,648".to_string();
    }

    // Handle the case of 0
    if num == 0 {
        return "0".to_string();
    }

    // Determine the sign
    if num < 0 {
        sign = -1;
        num = -num;
    }

    // Convert the number to a string
    let mut digits = Vec::with_capacity(MAX_DIGITS);
    while num > 0 {
        let digit = (num % 10) as u8 + b'0';
        digits.push(digit);
        if digits.len() % 4 == 0 && num > 10 {
            digits.push(b',');
        }
        num /= 10;
    }

    // Add the sign if necessary
    if sign == -1 {
        digits.push(b'-');
    }

    // Reverse the digits
    digits.reverse();

    // Convert the digits to a string
    String::from_utf8(digits).unwrap()
}

fn main() {
    let n = 0;
    let str = repr(n);
    println!("{}", str);
}