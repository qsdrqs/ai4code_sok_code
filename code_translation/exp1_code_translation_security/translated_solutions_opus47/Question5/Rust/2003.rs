const MAX_LEN: u32 = 16;

fn num_to_string(num: i32) -> Option<String> {
    if num == 0 {
        return Some(String::from("0"));
    }
    
    let mut is_neg = false;
    let mut num = num;
    if num < 0 {
        is_neg = true;
        num = -num;
    }
    
    let mut cur_num = num;
    let mut total_digits: i32 = 0;
    while cur_num > 0 {
        cur_num = cur_num / 10;
        total_digits += 1;
    }
    let num_commas = (total_digits - 1) / 3;
    let mut str_len = total_digits + num_commas;
    if is_neg {
        str_len += 1;
    }
    
    let mut s: Vec<u8> = vec![0u8; str_len as usize];
    
    cur_num = num;
    let mut index: i32 = 0;
    let mut digits: i32 = 0;
    while cur_num > 0 {
        let cur_char = ((cur_num % 10) + 48) as u8;
        s[(str_len - index - 1) as usize] = cur_char;
        cur_num = cur_num / 10;
        index += 1;
        digits += 1;
        if digits % 3 == 0 {
            s[(str_len - index - 1) as usize] = b',';
            index += 1;
        }
    }
    
    if is_neg {
        s[0] = b'-';
    }
    
    String::from_utf8(s).ok()
}

fn main() {
    if let Some(s) = num_to_string(-5305000) {
        print!("{}", s);
    }
}