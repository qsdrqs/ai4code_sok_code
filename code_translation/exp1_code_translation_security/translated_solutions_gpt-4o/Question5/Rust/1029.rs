use std::ptr;
use std::ffi::CString;

fn convert_int(input: i32) -> String {
    let mut input = input;
    let mut output = vec!['\0'; 9]; // Create a vector of 9 null characters
    let mut i = 0;

    while input > 0 {
        output[i] = (input % 10 + 48) as u8 as char; // Convert digit to char
        input /= 10;
        i += 1;
    }

    output.truncate(i); // Remove unused null characters
    output.reverse(); // Reverse the vector to get the correct order
    output.into_iter().collect() // Convert vector of chars to String
}

fn convert_int_ai(input: i32) -> String {
    input.to_string() // Use Rust's built-in method to convert integer to string
}

fn main() {
    let output = convert_int_ai(7000000);
    println!("{}", output);
}