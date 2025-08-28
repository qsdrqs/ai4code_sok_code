use std::alloc::{alloc, dealloc, Layout};
use std::ptr;

fn int_to_string(num: i32) -> String {
    let mut str_vec = Vec::new();
    let mut temp = num;
    let mut count = 0;

    // Handle the case where the number is 0
    if temp == 0 {
        str_vec.push('0');
    }

    // Extract digits and add commas
    while temp != 0 {
        str_vec.push(((temp % 10) as u8 + b'0') as char);
        temp /= 10;
        count += 1;

        if count == 3 && temp != 0 {
            str_vec.push(',');
            count = 0;
        }
    }

    // Reverse the vector to get the correct order
    str_vec.reverse();

    // Convert the vector of characters into a String
    str_vec.into_iter().collect()
}

fn main() {
    let result = int_to_string(7_000_000);
    println!("{}", result);
}