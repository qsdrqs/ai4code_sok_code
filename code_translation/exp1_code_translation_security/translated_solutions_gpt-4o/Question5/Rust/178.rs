use std::cmp;
use std::fmt;

fn convert_to_string(num: i32) -> String {
    // Convert the number to a string
    let string = num.to_string();
    let len = string.len();

    // Calculate the number of commas needed
    let num_of_commas = (len as f64 / 3.0).ceil() as usize - 1;
    println!("{}", num_of_commas);

    // Create a new string with enough space for commas
    let mut new_string = String::with_capacity(len + num_of_commas);

    // Iterate through the original string and insert commas
    for (i, c) in string.chars().enumerate() {
        new_string.push(c);
        if (len - i - 1) % 3 == 0 && i != len - 1 {
            new_string.push(',');
        }
    }

    new_string
}

fn main() {
    let result = convert_to_string(2000);
    println!("{}", result);
}