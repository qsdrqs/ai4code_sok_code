use std::fmt::Write; // For the write! macro
use std::string::String;

fn convert(num: i32) -> String {
    // Create a new String to hold the result
    let mut result = String::new();

    // Write the number into the string
    write!(&mut result, "{}", num).expect("Failed to write to string");

    result
}

fn main() {
    let ans = convert(700000);
    println!("{}", ans);
}