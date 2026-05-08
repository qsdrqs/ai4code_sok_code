fn int_to_string(mut num: i32) -> String {
    let mut len: usize = 0;
    let mut temp = num;
    while temp != 0 {
        temp /= 10;
        len += 1;
    }
    let mut str = vec![0u8; len];
    for i in (0..len).rev() {
        str[i] = (num % 10) as u8 + b'0';
        num /= 10;
    }
    String::from_utf8(str).unwrap()
}

fn int_to_string_with_commas(num: i32) -> String {
    let str = int_to_string(num);
    let len = str.len();
    let comma_count = if len == 0 { 0 } else { (len - 1) / 3 };
    let mut new_str = vec![0u8; len + comma_count];
    let str_bytes = str.as_bytes();

    let mut j: usize = 0;
    let mut k = 0;
    for i in 0..(len + comma_count) {
        if (len - j) % 3 == 0 && k != 1 && i != 0 {
            new_str[i] = b',';
            k = 1;
        } else {
            new_str[i] = str_bytes[j];
            j += 1;
            if k == 1 {
                k = 0;
            }
        }
    }
    String::from_utf8(new_str).unwrap()
}

fn main() {
    let num: i32 = 7654321;
    let str = int_to_string_with_commas(num);
    println!("{}", str);
}