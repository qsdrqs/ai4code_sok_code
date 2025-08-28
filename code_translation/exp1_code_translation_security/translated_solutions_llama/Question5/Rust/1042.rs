use std::alloc::{alloc, dealloc, Layout};
use std::fmt;

// Function to convert an integer into a string with commas as thousand separators
fn int_to_string(num: i32) -> *mut i8 {
    let len = num.to_string().len();
    let mut str_len = len;
    if len > 3 {
        str_len += (len - 1) / 3;
    }
    let layout = Layout::from_size_align(str_len + 1, 1).unwrap();
    let str_ptr = alloc(layout) as *mut i8;

    let mut i = 0;
    let mut num = num;
    let mut count = 0;
    let mut str_ptr_mut = str_ptr;

    // Convert the integer into a string with commas
    while num != 0 {
        let digit = (num % 10) as u8 + b'0' as u8;
        unsafe { *str_ptr_mut = digit as i8; }
        str_ptr_mut = str_ptr_mut.add(1);
        num /= 10;
        count += 1;
        if count == 3 && num != 0 {
            unsafe { *str_ptr_mut = b',' as i8; }
            str_ptr_mut = str_ptr_mut.add(1);
            count = 0;
        }
    }

    // Null-terminate the string
    unsafe { *str_ptr_mut = 0; }

    // Reverse the string
    let mut start = str_ptr;
    let mut end = str_ptr_mut.sub(1);
    while start < end {
        let temp = unsafe { *start };
        unsafe { *start = *end; }
        unsafe { *end = temp; }
        start = start.add(1);
        end = end.sub(1);
    }

    str_ptr
}

fn main() {
    let str_ptr = int_to_string(7000000);
    unsafe {
        println!("{}", str_ptr as *const _ as *const i8);
        dealloc(str_ptr as *mut _, Layout::from_size_align(20, 1).unwrap());
    }
}