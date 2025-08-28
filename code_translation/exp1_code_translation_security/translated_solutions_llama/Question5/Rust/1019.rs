use std::alloc::{alloc, dealloc, Layout};
use std::fmt;

// Function to calculate the length of a number as a string
fn string_length(num: i32) -> usize {
    if num == 0 {
        1
    } else {
        (num.abs().ilog10() + 1) as usize
    }
}

// Function to convert an integer to a string
fn string_rep(num: i32) -> *mut u8 {
    let len = string_length(num);
    let layout = Layout::from_size_align(len + 1, 1).unwrap();
    let str_ptr = alloc(layout) as *mut u8;

    // Convert the number to a string
    let s = format!("{}", num);
    unsafe {
        std::ptr::copy(s.as_bytes().as_ptr(), str_ptr, len);
        *(str_ptr.add(len)) = b'\0'; // Add null terminator
    }

    str_ptr
}

fn main() {
    let leet = 1337;
    let leet_str = string_rep(leet);

    // Print the string
    unsafe {
        let c_str = std::ffi::CStr::from_bytes_with_nul(std::slice::from_raw_parts(leet_str, string_length(leet) + 1)).unwrap();
        println!("{}", c_str.to_str().unwrap());
    }

    // Free the allocated memory
    let layout = Layout::from_size_align(string_length(leet) + 1, 1).unwrap();
    unsafe {
        dealloc(leet_str as *mut std::ffi::c_void, layout);
    }
}