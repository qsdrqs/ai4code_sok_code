use std::iter::FromIterator;

fn reverse(s: &mut String) {
    let mut chars: Vec<char> = s.chars().collect();
    chars.reverse();
    *s = String::from_iter(chars);
}

fn itoa(n: i32) -> String {
    let mut num = n;
    let mut result = String::new();
    let is_negative = num < 0;

    if is_negative {
        num = -num;
    }

    loop {
        result.push((num % 10) as u8 as char);
        num /= 10;
        if num == 0 {
            break;
        }
    }

    if is_negative {
        result.push('-');
    }

    reverse(&mut result);
    result
}

fn insert_comma(s: &mut String, pos: usize) {
    s.insert(pos, ',');
}

fn int_to_string(num: i32) -> String {
    let mut s = itoa(num);
    let mut length = s.len();

    // Insert commas every 3 digits from the right
    let mut i = length as isize - 3;
    while i > 0 {
        insert_comma(&mut s, i as usize);
        i -= 3;
    }

    s
}

fn main() {
    let num = 7000;
    let result = int_to_string(num);
    println!("{}", result);
}