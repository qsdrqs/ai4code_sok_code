use std::fmt;

// Helper function to insert a comma into a string at a specified index
fn insert(s: &str, index: usize) -> String {
    let mut parts: Vec<&str> = s.split_at(index).into();
    parts.1 = &s[index..];
    format!("{}{}", parts.0, ",".to_string() + parts.1)
}

// Function to represent a number as a string with commas
fn string_repre(num: i32) -> String {
    let mut str_num = num.to_string();
    let mut result = String::new();
    let mut count = 0;

    for c in str_num.chars().rev() {
        result.push(c);
        count += 1;
        if count == 3 {
            result.push(',');
            count = 0;
        }
    }

    result.chars().rev().collect()
}

fn main(argc: i32, argv: Vec<String>) -> String {
    // Note: argc and argv in Rust are not directly accessible like in C.
    // This example assumes you're trying to format the number 1000 for simplicity.
    let num = 1000;
    string_repre(num)
}

fn main() {
    println!("{}", main(0, Vec::new()));
}