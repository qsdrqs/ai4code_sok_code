use std::fmt;

// Function to reverse a string
fn reverse_str(s: &mut String) {
    let bytes = s.as_bytes_mut();
    let mut left = 0;
    let mut right = bytes.len() - 1;
    while left < right {
        bytes.swap(left, right);
        left += 1;
        right -= 1;
    }
}

// Function to add commas to a number
fn add_commas(num: u32) -> String {
    let mut num_str = num.to_string();
    let mut count = 0;
    let mut result = String::new();

    for c in num_str.chars().rev() {
        result.push(c);
        count += 1;
        if count == 3 && num_str.len() > result.len() {
            result.push(',');
            count = 0;
        }
    }

    reverse_str(&mut result);
    result
}

fn main() {
    let num: u32 = 1000000;
    let str = add_commas(num);
    println!("{}", str);
}