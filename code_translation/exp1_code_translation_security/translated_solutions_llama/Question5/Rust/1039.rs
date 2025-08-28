use std::fmt;

// Function to get the length of an integer
fn get_length(num: i32) -> i32 {
    let mut len = 0;
    let mut num_copy = num;
    if num_copy == 0 {
        return 1;
    }
    while num_copy != 0 {
        num_copy = num_copy / 10;
        len += 1;
    }
    len
}

// Function to convert an integer to a string
fn convert_to_string(num: i32) -> String {
    num.to_string()
}

fn main() {
    let num = 7000000;
    println!("{}", get_length(num));
    println!("{}", convert_to_string(5));
}