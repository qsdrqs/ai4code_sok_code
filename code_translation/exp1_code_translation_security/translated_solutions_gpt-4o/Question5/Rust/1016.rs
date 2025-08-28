use std::alloc::{alloc, dealloc, Layout};
use std::ptr;
use std::ffi::CString;

fn int_to_string(mut num: i32) -> Option<String> {
    let mut temp = num;
    let mut len = 0;
    let mut counter = 0;

    // Calculate the length of the string representation
    while temp != 0 {
        temp /= 10;
        len += 1;
        counter += 1;

        // Add commas for formatting
        if counter == 4 {
            len += 1;
            counter = 1;
        }
    }

    // Handle negative numbers
    let mut stop = 0;
    if num < 0 {
        len += 1; // +1 for the minus sign
        stop = 1;
    }

    // Allocate memory for the string
    let layout = Layout::array::<u8>(len + 1).ok()?;
    let str_ptr = unsafe { alloc(layout) };
    if str_ptr.is_null() {
        return None;
    }

    // Fill the string with characters
    let mut count = 0;
    let mut i = len - 1;
    while i >= stop {
        count += 1;
        if count == 4 {
            unsafe {
                ptr::write(str_ptr.add(i), b',' as u8);
            }
            count = 0;
            if i == 0 {
                break;
            }
            i -= 1;
            continue;
        }

        unsafe {
            ptr::write(str_ptr.add(i), (num.abs() % 10) as u8 + b'0');
        }
        num /= 10;
        if i == 0 {
            break;
        }
        i -= 1;
    }

    // Add the minus sign if the number is negative
    if stop == 1 {
        unsafe {
            ptr::write(str_ptr, b'-' as u8);
        }
    }

    // Null-terminate the string
    unsafe {
        ptr::write(str_ptr.add(len), b'\0' as u8);
    }

    // Convert the raw pointer to a Rust String
    let c_str = unsafe { CString::from_raw(str_ptr as *mut i8) };
    let result = c_str.into_string().ok();

    // Deallocate the memory
    unsafe {
        dealloc(str_ptr, layout);
    }

    result
}

fn main() {
    let num = -10099870;
    if let Some(str) = int_to_string(num) {
        println!("{}", str);
    } else {
        eprintln!("Failed to convert integer to string");
    }
}