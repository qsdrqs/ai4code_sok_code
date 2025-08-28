use std::fmt::Write;

fn string_it(value: i32) -> String {
    let mut result = String::new();

    // Handle negative values
    let mut value = if value < 0 {
        result.push('-');
        -value
    } else {
        value
    };

    // Convert the integer to a string
    let mut digits = value.to_string();

    // Insert commas
    let length = digits.len();
    let comma_count = (length - 1) / 3;
    let mut end = length;

    for _ in 0..comma_count {
        end -= 3;
        digits.insert(end, ',');
    }

    result.push_str(&digits);
    result
}

fn main() {
    println!("   {}", string_it(-123457));
}