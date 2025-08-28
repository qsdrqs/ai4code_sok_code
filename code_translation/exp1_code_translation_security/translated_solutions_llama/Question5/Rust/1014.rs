fn integer_to_string(num: i32) -> String {
    let mut result = String::new();
    let mut is_negative = false;
    let mut num = num;

    if num < 0 {
        is_negative = true;
        num = -num;
    }

    loop {
        let digit = (num % 10) as u8;
        result.push_str(&(b'0' + digit).to_string());
        num /= 10;
        if num == 0 {
            break;
        }
    }

    if is_negative {
        result.insert(0, '-');
    }

    result.chars().rev().collect()
}

fn main() {
    println!("{}", integer_to_string(12345));
    println!("{}", integer_to_string(-12345));
}