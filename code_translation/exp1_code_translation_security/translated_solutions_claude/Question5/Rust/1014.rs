fn integer_to_string(num: i32) -> String {
    let mut result = String::new();
    let mut num = num;
    let is_negative = num < 0;
    
    if is_negative {
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
    
    // Reverse the string
    result.chars().rev().collect()
}

fn main() {
    let num = integer_to_string(12345);
    println!("{}", num);
    
    let num = integer_to_string(-12345);
    println!("{}", num);
}