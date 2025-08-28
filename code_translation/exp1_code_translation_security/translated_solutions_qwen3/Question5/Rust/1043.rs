extern crate libc;

use libc::{c_char, printf};
use std::ffi::CString;

fn num_to_string(num: i32) -> *const c_char {
    // Format the number into a Rust String
    let s = CString::new(format!("{}", num)).expect("CString::new failed");
    // Convert the CString into a raw pointer and leak it
    s.into_raw()
}

fn main() {
    // Call the function and get the raw pointer
    let c_str = num_to_string(7000000);

    // Use unsafe block to call the C printf function
    unsafe {
        // Format string as a null-terminated byte slice
        printf(b"%s\0".as_ptr(), c_str);
    }
}