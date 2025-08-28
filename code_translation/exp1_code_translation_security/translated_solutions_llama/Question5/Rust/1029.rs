use std::alloc::{alloc, dealloc, Layout};
use std::fmt;

// Function to convert an integer to a string manually
fn convert_int(input: i32) -> String {
    let mut output = String::new();
    let mut input = input;

    if input == 0 {
        output.push('0');
    } else {
        while input != 0 {
            let digit = (input % 10) as u8 + b'0';
            output.insert(0, digit as char);
            input /= 10;
        }
    }

    output
}

// Function to convert an integer to a string using std::fmt
fn convert_int_ai(input: i32) -> String {
    format!("{}", input)
}

fn main() {
    let output = convert_int_ai(7000000);
    println!("{}", output);
}