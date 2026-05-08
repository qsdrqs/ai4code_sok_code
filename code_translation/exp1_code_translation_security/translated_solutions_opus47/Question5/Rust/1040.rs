fn num_digits(mut n: i32) -> i32 {
    let mut digits = 0;
    
    while n != 0 {
        n /= 10;
        digits += 1;
    }
    
    digits
}

fn int_to_string_with_commas(mut value: i32) -> String {
    let is_negative = value < 0;
    if is_negative {
        value *= -1;
    }
    let mut digits = num_digits(value);
    let mut num_commas = digits / 3;
    if digits % 3 == 0 {
        num_commas -= 1;
    }
    let s = format!("{}", value);
    let size = (digits + num_commas + 1) as usize;
    let mut dest = vec![0u8; size];
    let s_bytes = s.as_bytes();
    let mut index: i32 = size as i32 - 2;
    while digits > 0 {
        if index >= 0 {
            dest[index as usize] = s_bytes[(digits - 1) as usize];
        }
        index -= 1;
        if digits % 3 == 0 && digits > 0 && index >= 0 {
            dest[index as usize] = b',';
            index -= 1;
        }
        digits -= 1; // Note: the original C code was missing this decrement (infinite loop bug)
    }
    dest[size - 1] = 0; // null terminator (matches C code)
    
    let result = std::str::from_utf8(&dest[..size - 1]).unwrap().to_string();
    print!("{}", result);
    result
}

fn main() {
    int_to_string_with_commas(7000000);
}