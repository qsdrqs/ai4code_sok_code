use std::fmt::Write;

fn num_to_str(num: i32) -> String {
    let mut str = num.to_string(); // Convert the number to a string
    let mut result = String::new();

    if num == 0 {
        return str; // If the number is 0, return "0"
    }

    let is_negative = num < 0;
    let mut chars: Vec<char> = str.chars().collect();

    if is_negative {
        chars.remove(0); // Remove the negative sign for now
    }

    let length = chars.len();
    let num_commas = (length - 1) / 3;

    let mut digits_moved = 0;
    for (i, c) in chars.iter().rev().enumerate() {
        if digits_moved == 3 {
            result.insert(0, ','); // Insert a comma
            digits_moved = 0;
        }
        result.insert(0, *c); // Insert the digit
        digits_moved += 1;
    }

    if is_negative {
        result.insert(0, '-'); // Add the negative sign back
    }

    result
}

fn main() {
    let num = 7042;
    let formatted_str = num_to_str(num);

    println!("Output: {}", formatted_str);
}