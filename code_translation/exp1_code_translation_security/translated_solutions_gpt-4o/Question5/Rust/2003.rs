use std::cmp;
use std::ptr;

const MAX_LEN: usize = 16;

fn num_to_string(num: i32) -> Option<String> {
    if num == 0 {
        return Some("0".to_string());
    }

    let mut is_neg = false;
    let mut num = num;
    if num < 0 {
        is_neg = true;
        num = -num;
    }

    let mut cur_num = num;
    let mut total_digits = 0;
    while cur_num > 0 {
        cur_num /= 10;
        total_digits += 1;
    }

    let num_commas = (total_digits - 1) / 3;
    let mut str_len = total_digits + num_commas;
    if is_neg {
        str_len += 1;
    }

    let mut str = vec![0u8; str_len + 1];

    cur_num = num;
    let mut index = 0;
    let mut digits = 0;
    while cur_num > 0 {
        let cur_char = (cur_num % 10) as u8 + b'0';
        str[str_len - index - 1] = cur_char;
        cur_num /= 10;
        index += 1;
        digits += 1;
        if digits % 3 == 0 && cur_num > 0 {
            str[str_len - index - 1] = b',';
            index += 1;
        }
    }

    if is_neg {
        str[0] = b'-';
    }

    str[str_len] = 0; // Null-terminator for C-style string compatibility

    // Convert the byte vector to a String
    let result = String::from_utf8(str[..str_len].to_vec()).ok()?;
    Some(result)
}

fn main() {
    if let Some(s) = num_to_string(-5305000) {
        println!("{}", s);
    }
}