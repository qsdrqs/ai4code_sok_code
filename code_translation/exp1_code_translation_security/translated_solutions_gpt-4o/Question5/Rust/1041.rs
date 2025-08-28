use std::iter::FromIterator;

fn main() {
    let num = 1_000_000;
    let str_with_commas = add_commas(num);
    println!("{}", str_with_commas);
}

fn reverse_str(s: &mut String) {
    let bytes = unsafe { s.as_bytes_mut() };
    bytes.reverse();
}

fn add_commas(mut num: u32) -> String {
    let mut result = String::new();
    let mut count = 0;

    while num != 0 {
        let digit = (num % 10) as u8;
        result.push((digit + b'0') as char);
        num /= 10;
        count += 1;

        if count == 3 && num != 0 {
            result.push(',');
            count = 0;
        }
    }

    if result.is_empty() {
        result.push('0');
    }

    reverse_str(&mut result);
    result
}