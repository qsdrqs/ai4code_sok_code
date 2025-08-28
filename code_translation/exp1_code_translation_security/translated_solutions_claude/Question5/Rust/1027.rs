use std::io::{self, Write};

fn convert(i: i32) -> i32 {
    // Format the number with thousands separators
    let formatted = format_with_separators(i);
    println!("{}", formatted);
    0
}

fn format_with_separators(num: i32) -> String {
    let num_str = num.to_string();
    let mut result = String::new();
    let chars: Vec<char> = num_str.chars().collect();
    
    for (i, ch) in chars.iter().enumerate() {
        if i > 0 && (chars.len() - i) % 3 == 0 {
            result.push(',');
        }
        result.push(*ch);
    }
    
    result
}

fn main() {
    let a = 400;
    convert(a);
}