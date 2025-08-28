fn main() {
    let num = 7000;
    let s = number_to_string(num);
    println!("{}", s);
}

pub fn number_to_string(num: i32) -> String {
    if num == 0 {
        return "0".to_string();
    }

    let mut s = String::new();
    let mut n = num.abs();
    while n > 0 {
        let digit = (n % 10) as u8;
        s.push((digit + b'0') as char);
        n /= 10;
    }
    if num < 0 {
        s.push('-');
    }

    let original_length = s.len();
    let mut positions = Vec::new();
    let mut i = 3;
    while i <= original_length {
        positions.push(i);
        i += 4;
    }

    for pos in positions {
        s.insert(pos, ',');
    }

    if s.ends_with(',') {
        s.pop();
    }

    s.chars().rev().collect()
}