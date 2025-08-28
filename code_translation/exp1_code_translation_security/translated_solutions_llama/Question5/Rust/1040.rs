use std::fmt;

// Function to calculate the number of digits in a given integer
fn num_digits(n: i32) -> i32 {
    let mut digits = 0;
    let mut n = n.abs(); // Take absolute value to handle negative numbers

    while n != 0 {
        n /= 10;
        digits += 1;
    }

    digits
}

// Function to convert an integer to a string with commas
fn int_to_string_with_commas(value: i32) -> String {
    let is_negative = value < 0;
    let mut value = value.abs(); // Take absolute value to handle negative numbers
    let digits = num_digits(value);
    let num_commas = digits / 3;

    let mut result = String::new();

    // Convert integer to string
    let str_value = value.to_string();

    // Add commas
    let mut index = str_value.len() - 1;
    let mut count = 0;
    for c in str_value.chars().rev() {
        result.push(c);
        count += 1;

        if count == 3 && index != 0 {
            result.push(',');
            count = 0;
        }

        index -= 1;
    }

    // Reverse the string
    let mut result: String = result.chars().rev().collect();

    // Add negative sign if necessary
    if is_negative {
        result.insert(0, '-');
    }

    println!("{}", result);
    result
}

fn main() {
    int_to_string_with_commas(7000000);
}