/// A function that reverses a string in place
fn strrev(input: &mut [u8]) {
    let len = input.len();
    if len > 1 {
        let mut start = 0;
        let mut end = len - 1;
        while end > start {
            input.swap(start, end);
            start += 1;
            end -= 1;
        }
    }
}

/// A function that takes a signed integer num and returns a string representation of the integer
fn integer_to_string(mut num: i32) -> String {
    let mut result: Vec<u8> = Vec::with_capacity(100);
    let mut is_negative = false;

    if num < 0 {
        is_negative = true;
        num = -num;
    }

    // do-while equivalent: loop that runs at least once
    loop {
        let digit = num % 10;
        result.push(b'0' + digit as u8);
        num /= 10;
        if num == 0 {
            break;
        }
    }

    if is_negative {
        result.push(b'-');
    }

    strrev(&mut result);
    String::from_utf8(result).unwrap()
}

fn main() {
    let num = integer_to_string(12345);
    println!("{}", num);
    // No need to free - Rust automatically drops the String when it goes out of scope

    let num = integer_to_string(-12345);
    println!("{}", num);
}