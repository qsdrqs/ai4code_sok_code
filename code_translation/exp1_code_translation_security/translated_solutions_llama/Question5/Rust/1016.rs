use std::alloc::{alloc, dealloc, Layout};

fn int_to_string(num: i32) -> *mut u8 {
    let mut temp = num;
    let mut len = 0;
    let mut counter = 0;

    // Calculate the length of the string
    let mut abs_num = temp.abs();
    while abs_num != 0 {
        abs_num /= 10;
        len += 1;
        counter += 1;
        // Add 1 for comma every 4 digits
        if counter == 4 {
            len += 1;
            counter = 1;
        }
    }

    // Add 1 for the null terminator and 1 for the minus sign if necessary
    if num < 0 {
        len += 1;
    }

    // Allocate memory for the string
    let layout = Layout::from_size_align(len + 1, 1).unwrap();
    let str_ptr = alloc(layout) as *mut u8;

    if str_ptr.is_null() {
        return std::ptr::null_mut();
    }

    let mut stop = 0;
    if num < 0 {
        stop = 1;
        unsafe { *str_ptr.offset(0) = b'-'; }
    }

    let mut count = 0;
    let mut num = num.abs();
    for i in (0..len).rev() {
        count += 1;
        if count == 4 && i != stop {
            unsafe { *str_ptr.offset(i as isize) = b','; }
            count = 0;
            continue;
        }
        unsafe { *str_ptr.offset(i as isize) = (num % 10) as u8 + b'0'; }
        num /= 10;
    }

    // Add null terminator
    unsafe { *str_ptr.offset(len as isize) = 0; }

    str_ptr
}

fn main() {
    let num: i32 = -10099870;
    let str_ptr = int_to_string(num);

    if !str_ptr.is_null() {
        let c_str = unsafe { std::ffi::CStr::from_ptr(str_ptr as *const i8) };
        println!("{}", c_str.to_str().unwrap());
        let layout = Layout::from_size_align(c_str.len() + 1, 1).unwrap();
        unsafe { dealloc(str_ptr as *mut u8, layout); }
    }
}