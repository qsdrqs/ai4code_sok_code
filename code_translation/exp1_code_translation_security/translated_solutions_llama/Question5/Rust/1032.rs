use std::alloc::{alloc, dealloc, Layout};

fn int_to_string(num: i32) -> String {
    let mut len = 0;
    let mut temp = num;
    if temp == 0 {
        return "0".to_string();
    }

    let mut is_negative = false;
    if temp < 0 {
        temp = -temp;
        is_negative = true;
    }

    while temp != 0 {
        temp /= 10;
        len += 1;
    }

    let mut str = String::with_capacity(len + if is_negative { 1 } else { 0 });
    temp = if is_negative { -num } else { num };

    for _ in 0..len {
        let digit = (temp % 10) as u8;
        str.push(digit as char + b'0');
        temp /= 10;
    }

    if is_negative {
        str.push('-');
    }

    str.chars().rev().collect()
}

fn int_to_string_with_commas(num: i32) -> String {
    let str = int_to_string(num);
    let len = str.len();
    let comma_count = (len - 1) / 3;
    let mut new_str = String::with_capacity(len + comma_count);

    let mut j = 0;
    for i in 0..len + comma_count {
        if (len - j) % 3 == 0 && i != 0 {
            new_str.push(',');
        } else {
            new_str.push(str.chars().nth(j).unwrap());
            j += 1;
        }
    }

    new_str
}

fn main() {
    let num: i32 = 7654321;
    let str = int_to_string_with_commas(num);
    println!("{}", str);
}