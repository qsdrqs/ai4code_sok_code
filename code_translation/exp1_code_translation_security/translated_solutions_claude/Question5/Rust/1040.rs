use std::io::{self, Write};

fn num_digits(mut n: i32) -> i32 {
    let mut digits = 0;
    
    while n != 0 {
        n /= 10;
        digits += 1;
    }
    
    digits
}

fn int_to_string_with_commas(value: i32) -> String {
    let is_negative = value < 0;
    let mut value = if is_negative { -value } else { value };
    
    let digits = num_digits(value);
    let mut num_commas = digits / 3;
    if digits % 3 == 0 {
        num_commas -= 1;
    }
    
    let s = format!("{}", value);
    let size = (digits + num_commas + 1) as usize;
    let mut dest = vec!['\0'; size];
    let mut index = size - 2;
    let mut digits_remaining = digits;
    
    while digits_remaining > 0 {
        dest[index] = s.chars().nth((digits_remaining - 1) as usize).unwrap();
        index -= 1;
        digits_remaining -= 1;
        
        if digits_remaining % 3 == 0 && digits_remaining > 0 {
            dest[index] = ',';
            index -= 1;
        }
    }
    
    let result: String = dest.iter().take(size - 1).collect();
    print!("{}", result);
    io::stdout().flush().unwrap();
    
    result
}

fn main() {
    int_to_string_with_commas(7000000);
}