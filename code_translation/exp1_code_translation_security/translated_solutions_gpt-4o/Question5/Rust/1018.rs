use std::alloc::{alloc, dealloc, Layout};
use std::ptr;
use std::slice;

fn change(input: i32) -> String {
    let mut count = 0;
    let mut tmp = input;
    // Count the number of digits in the input
    while tmp != 0 {
        tmp /= 10;
        count += 1;
    }

    // Calculate the size of the resulting string (including commas)
    let num = count + count / 3;
    let mut result = vec![0u8; num + 1]; // +1 for null terminator (not strictly needed in Rust)

    let mut mid = 0;
    let mut input = input;
    for i in (0..num).rev() {
        if mid == 3 {
            mid = 0;
            result[i] = b','; // Add a comma
            continue;
        }
        result[i] = (input % 10) as u8 + b'0'; // Add the digit
        input /= 10;
        mid += 1;
    }

    // Convert the result vector to a String
    String::from_utf8(result).unwrap()
}

fn main() {
    let snum = change(700_000_000);
    println!("{}", snum);
}