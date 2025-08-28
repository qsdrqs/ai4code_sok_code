use std::cmp;
use std::iter::FromIterator;

fn repr(n: i32) -> String {
    if n == -2147483648 {
        // Handle the edge case separately
        return "-2,147,483,648".to_string();
    }

    let mut n = n;
    let mut sign = 1;
    if n == 0 {
        return "0".to_string();
    }
    if n < 0 {
        sign = -1;
        n *= -1;
    }

    let mut digits = Vec::new();
    while n > 0 {
        digits.push((n % 10) as u8 + b'0');
        if (digits.len() + 1) % 4 == 0 && n >= 10 {
            digits.push(b',');
        }
        n /= 10;
    }

    if sign == -1 {
        digits.push(b'-');
    }

    digits.reverse();
    String::from_iter(digits.into_iter().map(|c| c as char))
}

fn main() {
    let n = 0;
    let str_repr = repr(n);
    println!("{}", str_repr);
}