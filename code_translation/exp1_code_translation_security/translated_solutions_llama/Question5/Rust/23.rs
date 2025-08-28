use std::fmt;

fn int_to_string(x: i32) -> String {
    let mut result = String::new();
    let mut x = x.abs(); // handle negative numbers
    if x == 0 {
        result.push_str("0");
    }

    while x > 0 {
        if result.len() > 0 && result.len() % 3 == 0 {
            result.insert(0, ',');
        }
        result.insert(0, (x % 10) as u8 as char);
        x /= 10;
    }

    if x < 0 {
        result.insert(0, '-');
    }

    result
}

fn main() {
    println!("{}", int_to_string(100000));
    println!("test");
}