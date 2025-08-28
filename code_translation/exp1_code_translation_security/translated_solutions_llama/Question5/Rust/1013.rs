use std::alloc::{alloc, dealloc, Layout};
use std::fmt;

// Function to convert an integer to a string
fn convert(num: i32) -> *mut u8 {
    // Calculate the length of the string representation of the number
    let len = format!("{}", num).len();

    // Allocate memory for the string
    let layout = Layout::from_size_align(len + 1, 1).unwrap();
    let ptr = alloc(layout) as *mut u8;

    // Format the number into the allocated string
    let s = format!("{}", num);
    for (i, c) in s.bytes().enumerate() {
        unsafe {
            *ptr.add(i) = c;
        }
    }
    unsafe {
        *ptr.add(len) = b'\0'; // Add null terminator
    }

    ptr
}

fn main() {
    // Convert 700000 to a string
    let ans = convert(700000);

    // Print the string
    let s = unsafe { std::str::from_raw_parts(ans, 7) }; // Assuming 7 is the length of "700000\0"
    println!("{}", s);

    // Free the allocated memory
    let layout = Layout::from_size_align(7, 1).unwrap();
    unsafe {
        dealloc(ans as *mut _, layout);
    }
}