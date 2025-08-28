const MAX_LEN: u32 = 16;

fn num_to_string(num: i32) -> String {
    if num == 0 {
        return "0".to_string();
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
        cur_num = cur_num / 10;
        total_digits += 1;
    }
    let num_commas = (total_digits - 1) / 3;
    let mut str_len = total_digits + num_commas;
    if is_neg {
        str_len += 1;
    }
    
    let mut str_chars: Vec<char> = vec!['\0'; str_len];
    
    cur_num = num;
    let mut index = 0;
    let mut digits = 0;
    while cur_num > 0 {
        let cur_char = char::from((cur_num % 10) as u8 + 48);
        str_chars[str_len - index - 1] = cur_char;
        cur_num = cur_num / 10;
        index += 1;
        digits += 1;
        if digits % 3 == 0 && cur_num > 0 {
            str_chars[str_len - index - 1] = ',';
            index += 1;
        }
    }
    
    if is_neg {
        str_chars[0] = '-';
    }
    
    str_chars.into_iter().collect()
}

fn main() {
    print!("{}", num_to_string(-5305000));
}