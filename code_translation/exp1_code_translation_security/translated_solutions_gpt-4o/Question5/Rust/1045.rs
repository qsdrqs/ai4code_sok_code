use std::cmp::Ordering;

fn number_to_string(num: i32) -> String {
    let mut num_size = 0;
    let mut temp = num.abs();
    let mut comma_count = 0;

    // Calculate the number of digits in the number
    while temp > 0 {
        temp /= 10;
        num_size += 1;
    }

    // Calculate the number of commas needed
    comma_count = if num_size > 0 { (num_size - 1) / 3 } else { 0 };

    // Create a vector to store the characters
    let mut str_num = Vec::with_capacity(num_size + comma_count + if num < 0 { 1 } else { 0 });

    // Handle negative numbers
    let mut num = num;
    if num < 0 {
        str_num.push('-');
        num = -num;
    }

    // Convert number to string with commas
    for j in (0..num_size).rev() {
        let power_of_ten = 10_i32.pow(j as u32);
        let digit = (num / power_of_ten) as u8 + b'0';
        str_num.push(digit as char);
        num %= power_of_ten;

        if j % 3 == 0 && j != 0 {
            str_num.push(',');
        }
    }

    // Convert the vector of characters to a String
    str_num.into_iter().collect()
}

fn main() {
    let num = -7000000;
    let str_num = number_to_string(num);
    println!("{}", str_num);
}