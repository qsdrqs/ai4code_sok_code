use std::alloc::{alloc, Layout};
use std::ptr;

fn power(base: i32, exponent: i32) -> i32 {
    let mut result = 1;
    let mut exp = exponent;
    while exp > 0 {
        result *= base;
        exp -= 1;
    }
    result
}

fn number_to_string(num: i32) -> *mut i8 {
    let mut num_size = 0;
    let mut i = 0;
    let mut comma_count = 0;
    let mut temp = num;
    let mut num_mut = num;

    if num < 0 {
        temp = -temp;
    }
    
    while temp > 0 {
        temp /= 10;
        num_size += 1;
    }
    
    comma_count = (num_size - 1) / 3;
    
    let layout = Layout::from_size_align(
        (num_size + comma_count + 1) as usize, 
        1
    ).unwrap();
    let str_num = unsafe { alloc(layout) as *mut i8 };
    
    if num < 0 {
        unsafe {
            *str_num.offset(0) = b'-' as i8;
        }
        num_mut = -num_mut;
        i += 1;
    }
    
    let mut j = num_size - 1;
    while j >= 0 {
        unsafe {
            *str_num.offset(i) = ((num_mut / power(10, j)) as u8 + b'0') as i8;
        }
        num_mut %= power(10, j);
        i += 1;
        if (j % 3 == 0) && (j != 0) {
            unsafe {
                *str_num.offset(i) = b',' as i8;
            }
            i += 1;
        }
        j -= 1;
    }
    
    unsafe {
        *str_num.offset(i) = 0; // null terminator
    }
    
    str_num
}

fn main() {
    let num = -7000000;
    let str_num = number_to_string(num);
    
    // Print the C-style string
    unsafe {
        let mut i = 0;
        while *str_num.offset(i) != 0 {
            print!("{}", *str_num.offset(i) as u8 as char);
            i += 1;
        }
        println!();
    }
}