extern crate libc;

use std::ffi::c_char;
use libc::{malloc, snprintf, printf, free};

fn convert(num: i32) -> *mut c_char {
    // Calculate the length of the number as a string
    let len = num.to_string().len();

    // Allocate enough space for the number and null terminator
    let buffer = unsafe { malloc(len + 1) } as *mut c_char;

    if buffer.is_null() {
        eprintln!("failed");
    }

    // Use snprintf to format the number into the buffer
    unsafe {
        // Format string as a null-terminated byte string
        let format = b"%d\0" as *const u8 as *const c_char;
        snprintf(buffer, len + 1, format, num);
    }

    buffer
}

fn main() {
    let ans = convert(700000);

    unsafe {
        // Print the result using C-style printf
        let format = b"%s\n\0" as *const u8 as *const c_char;
        printf(format, ans);

        // Free the allocated memory
        free(ans as *mut _);
    }
}