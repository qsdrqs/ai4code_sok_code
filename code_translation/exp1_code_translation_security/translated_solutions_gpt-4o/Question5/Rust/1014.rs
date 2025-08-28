use std::iter::FromIterator;

/// A function that reverses a string
fn strrev(input: &mut String) {
    let mut chars: Vec<char> = input.chars().collect();
    chars.reverse();
    *input = String::from_iter(chars);
}

/// A function that takes a signed integer `num` and returns a string representation of the integer
fn integer_to_string(num: i32) -> String {
    let mut result = String::new();
    let mut num = num;
    let mut is_negative = false;

    if num < 0 {
        is_negative = true;
        num = -num;
    }

    loop {
        let digit = num % 10;
        result.push((b'0' + digit as u8) as char);
        num /= 10;
        if num == 0 {
            break;
        }
    }

    if is_negative {
        result.push('-');
    }

    strrev(&mut result);
    result
}

fn main() {
    let num = integer_to_string(12345);
    println!("{}", num);

    let num = integer_to_string(-12345);
    println!("{}", num);
}