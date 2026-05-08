const MAX_STR_LEN: usize = 128;

fn reverse_str(s: &str) -> String {
    let len = s.len();
    let bytes = s.as_bytes();
    let mut new_str = vec![0u8; len];
    for i in 0..len {
        new_str[i] = bytes[len - i - 1];
    }
    String::from_utf8(new_str).unwrap()
}

/// Return a string representation of a signed integer
///
/// # Arguments
/// * `num` - input number as a signed integer
///
/// # Returns
/// string representation of the signed integer
fn str_of_int(num: i32) -> String {
    let mut str_bytes: Vec<u8> = Vec::with_capacity(MAX_STR_LEN);
    let mut n = num;
    let mut is_neg = false;
    if n < 0 {
        is_neg = true;
        n = -n;
    }
    // do-while equivalent - ensures at least one iteration (handles n == 0)
    loop {
        str_bytes.push((n % 10) as u8 + b'0');
        n /= 10;
        if n == 0 {
            break;
        }
    }
    if is_neg {
        str_bytes.push(b'-');
    }
    let s = String::from_utf8(str_bytes).unwrap();
    reverse_str(&s)
}

fn main() {
    println!("{}", str_of_int(7000000));
}