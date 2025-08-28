use std::alloc::{alloc, Layout};
use std::ptr;
use std::ffi::CString;

fn string_it(value: i32) -> String {
    let mut value = value;
    let mut result = String::new();
    
    // Handle negative numbers
    if value < 0 {
        result.push('-');
        value = -value;
    }
    
    // Convert to string
    let mut str_num = value.to_string();
    let length = str_num.len();
    let comma_count = (length - 1) / 3;
    
    // Add commas from right to left
    let mut remaining_commas = comma_count;
    while remaining_commas > 0 {
        let insert_pos = length - (remaining_commas * 3);
        str_num.insert(insert_pos, ',');
        remaining_commas -= 1;
    }
    
    result.push_str(&str_num);
    result
}

fn main() {
    println!("   {}", string_it(-123457));
}