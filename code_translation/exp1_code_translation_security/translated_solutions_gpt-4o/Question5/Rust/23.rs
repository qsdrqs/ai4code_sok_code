use std::fmt::Write; // For `write!` macro

fn int_to_string(x: i32) -> String {
    // Convert the integer to a string
    let tempstring = x.to_string();

    // Create a new string to store the result
    let mut result = String::new();

    // Iterate over the characters in reverse order to insert commas
    let mut count = 0;
    for c in tempstring.chars().rev() {
        if count > 0 && count % 3 == 0 {
            result.push(','); // Add a comma every 3 digits
        }
        result.push(c);
        count += 1;
    }

    // Reverse the result to get the correct order
    result.chars().rev().collect()
}

fn main() {
    // Test the function
    let formatted_number = int_to_string(100000);
    println!("{}", formatted_number); // Output: "100,000"
    println!("test");
}