use std::fmt;

fn num_to_str(num: i32, str: &mut String) {
    str.clear();
    str.push_str(&num.to_string());

    if num == 0 {
        return;
    }

    let length = str.len();
    let mut num_commas = 0;

    if num > 0 {
        num_commas = (length - 1) / 3;
    } else {
        num_commas = (length - 2) / 3;
    }

    let mut chars: Vec<char> = str.chars().collect();
    let mut digits_moved = 0;
    let mut index = chars.len() - 1;

    for _ in 0..num_commas {
        chars.insert(index, ',');
        index -= 1;
        digits_moved = 0;
    }

    if num < 0 {
        chars.insert(0, '-');
    }

    *str = chars.into_iter().collect();
}

fn main() {
    let mut str = String::new();
    let num = 7042;

    num_to_str(num, &mut str);

    println!("Output: {}", str);
}