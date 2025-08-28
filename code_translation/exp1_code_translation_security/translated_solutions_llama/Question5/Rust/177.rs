use std::alloc::{alloc, dealloc, Layout};
use std::fmt;

// Function to convert an integer into a string with commas as thousand separators
fn int2str(num: i32) -> String {
    // Convert the integer into a string
    let num_str = num.to_string();

    // Calculate the length of the string with commas
    let length = num_str.len();
    let new_length = if length % 3 == 0 {
        length + length / 3 - 1
    } else {
        length + (length / 3) + 1
    };

    // Initialize the result string
    let mut result = String::with_capacity(new_length);

    // Add commas as thousand separators
    let mut counter = 0;
    for c in num_str.chars().rev() {
        result.push(c);
        counter += 1;
        if counter % 3 == 0 && counter != length {
            result.push(',');
        }
    }

    // Reverse the string
    result.chars().rev().collect()
}

fn main() {
    let num = 1234567;
    println!("{}", int2str(num));
}