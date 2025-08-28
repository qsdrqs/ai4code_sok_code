fn main() {
    let num = 7654321;
    let str = int_to_string_with_commas(num);
    println!("{}", str);
}

/// Converts an integer to a string without using standard library formatting.
pub fn int_to_string(num: i32) -> String {
    let mut len = 0;
    let mut temp = num;
    while temp != 0 {
        temp /= 10;
        len += 1;
    }

    if len == 0 {
        return String::new(); // Matches C behavior for num == 0
    }

    let mut buffer = vec!['\0'; len + 1];

    let mut num = num;
    for i in (0..len).rev() {
        buffer[i] = ((num % 10) as u8 + b'0') as char;
        num /= 10;
    }

    buffer.truncate(len);
    buffer.into_iter().collect()
}

/// Converts an integer to a string with commas as thousand separators.
pub fn int_to_string_with_commas(num: i32) -> String {
    let str = int_to_string(num);
    let len = str.len();
    if len == 0 {
        return str; // Matches C behavior for num == 0
    }

    let comma_count = (len - 1) / 3;
    let new_len = len + comma_count;
    let mut new_str = String::with_capacity(new_len);
    let str_chars: Vec<char> = str.chars().collect();

    let mut j = 0;
    let mut k = 0;

    for i in 0..new_len {
        if (len - j) % 3 == 0 && k != 1 && i != 0 {
            new_str.push(',');
            k = 1;
        } else {
            new_str.push(str_chars[j]);
            j += 1;
            if k == 1 {
                k = 0;
            }
        }
    }

    new_str
}