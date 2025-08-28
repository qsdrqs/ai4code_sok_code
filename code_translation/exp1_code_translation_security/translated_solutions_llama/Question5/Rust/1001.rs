use std::alloc::{alloc, dealloc, Layout};
use std::fmt;

// Function to convert an integer to a string
fn int_to_string(x: i32) -> String {
    format!("{}", x)
}

// Alternatively, if you want to manually handle memory
fn int_to_string_manual(x: i32) -> *mut u8 {
    let layout = Layout::from_size_align(12, 1).unwrap();
    let ptr = alloc(layout) as *mut u8;

    // SAFETY: Assuming the allocated memory is large enough to hold the string and null terminator
    unsafe {
        let bytes = format!("{}\0", x).into_bytes();
        std::ptr::copy(bytes.as_ptr(), ptr, bytes.len());
    }

    ptr
}

fn main() {
    let x = 123;
    let s = int_to_string(x);
    println!("{}", s);

    // Manual memory management example
    let manual_s = int_to_string_manual(x);
    // SAFETY: Assuming the string is null-terminated
    unsafe {
        println!("{}", std::str::from_raw_parts(manual_s as *const i8, 12));
        dealloc(manual_s as *mut u8, Layout::from_size_align(12, 1).unwrap());
    }
}