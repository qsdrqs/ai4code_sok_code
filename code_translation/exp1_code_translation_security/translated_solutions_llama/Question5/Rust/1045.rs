use std::alloc::{alloc, dealloc, Layout};

fn number_to_string(num: i32) -> String {
    let mut num = num;
    let mut num_size = 0;
    let mut temp = num.abs();

    // Calculate the number of digits
    while temp > 0 {
        temp /= 10;
        num_size += 1;
    }

    // Calculate the number of commas
    let comma_count = (num_size - 1) / 3;

    // Create a string buffer
    let mut str_num = String::with_capacity(num_size + comma_count + 1);

    // Handle negative numbers
    if num < 0 {
        str_num.push('-');
        num = -num;
    }

    // Convert the number to a string with commas
    let mut num_str = String::new();
    let mut num = num;
    while num > 0 {
        let digit = (num % 10) as u8 as char;
        num_str.insert(0, digit);
        num /= 10;

        // Add a comma every 3 digits
        if num > 0 && (num_size - num_str.len()) % 3 == 0 {
            num_str.insert(0, ',');
        }
    }

    str_num.push_str(&num_str);
    str_num
}

fn main() {
    let num = -7000000;
    let str_num = number_to_string(num);
    println!("{}", str_num);
}