use std::alloc::{alloc, dealloc, Layout};
use std::fmt;

// Define a buffer size
const BUFFER_SIZE: usize = 1024;

// Function to convert an i64 value into a string with commas
fn convert(value: i64) -> String {
    let mut buf = format!("{}", value);
    let mut result = String::new();

    // Iterate over the buffer in reverse order
    for (i, c) in buf.chars().rev().enumerate() {
        result.push(c);
        // Add a comma every three characters
        if (i + 1) % 3 == 0 && i + 1 != buf.len() {
            result.push(',');
        }
    }

    // Reverse the string to get the correct order
    result.chars().rev().collect()
}

fn main() {
    let test = convert(1000);
    println!("{}", test);
}