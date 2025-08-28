/// Reverses the input string and returns a new owned String.
pub fn reverse_str(s: &str) -> String {
    s.chars().rev().collect()
}

/// Converts a signed integer to its string representation.
pub fn str_of_int(num: i32) -> String {
    let (mut n, is_neg) = if num < 0 {
        (-num, true)
    } else {
        (num, false)
    };

    let mut s = String::new();

    loop {
        let digit = n % 10;
        s.push((digit as u8 + b'0') as char);
        n /= 10;
        if n == 0 {
            break;
        }
    }

    if is_neg {
        s.push('-');
    }

    reverse_str(&s)
}

fn main() {
    println!("{}", str_of_int(7000000));
}