pub fn integer_to_string(mut num: i32) -> String {
    if num == 0 {
        return "0".to_string();
    }

    let mut buffer = Vec::new();
    let is_negative = num < 0;
    if is_negative {
        num = -num;
    }

    while num > 0 {
        let digit = num % 10;
        buffer.push((b'0' + digit as u8) as char);
        num /= 10;
    }

    if is_negative {
        buffer.push('-');
    }

    buffer.reverse();

    buffer.into_iter().collect()
}

fn main() {
    let num1 = integer_to_string(12345);
    println!("{}", num1); // Output: 12345

    let num2 = integer_to_string(-12345);
    println!("{}", num2); // Output: -12345

    let num3 = integer_to_string(0);
    println!("{}", num3); // Output: 0
}