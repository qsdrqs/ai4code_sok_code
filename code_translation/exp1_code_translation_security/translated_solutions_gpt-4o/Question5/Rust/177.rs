use std::fmt::Write;

fn int2str(num: i32) -> String {
    // Convert the integer to a string
    let num_str = num.to_string();
    let length = num_str.len();

    // Calculate the new length with commas
    let new_length = if length % 3 == 0 {
        length + (length / 3) - 1
    } else {
        length + (length / 3)
    };

    // Create a new string with the required capacity
    let mut result = String::with_capacity(new_length);

    // Add characters to the result string in reverse order
    let mut counter = 0;
    for (i, ch) in num_str.chars().rev().enumerate() {
        if counter > 0 && counter % 3 == 0 {
            result.push(',');
        }
        result.push(ch);
        counter += 1;
    }

    // Reverse the result string to get the correct order
    result.chars().rev().collect()
}

fn main() {
    let num = 123456789;
    let formatted = int2str(num);
    println!("{}", formatted); // Output: "123,456,789"
}