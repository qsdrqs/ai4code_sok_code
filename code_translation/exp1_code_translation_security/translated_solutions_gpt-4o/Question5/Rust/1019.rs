use std::ffi::CString;
use std::os::raw::c_char;
use std::ptr;

fn string_rep(num: i32) -> *mut c_char {
    // Calculate the size of the string representation
    let size = ((num as f64).log10().floor() as usize + 1) + 1; // +1 for null terminator

    // Allocate memory for the string
    let mut buffer = vec![0u8; size];

    // Write the number into the buffer
    let c_string = CString::new(num.to_string()).expect("Failed to create CString");
    buffer[..c_string.as_bytes().len()].copy_from_slice(c_string.as_bytes());

    // Convert the buffer into a raw pointer
    let raw_ptr = buffer.as_mut_ptr() as *mut c_char;

    // Prevent Rust from deallocating the buffer
    std::mem::forget(buffer);

    raw_ptr
}

fn main() {
    let leet = 1337;

    // Get the string representation of the number
    let leet_str = string_rep(leet);

    // Print the string
    unsafe {
        if !leet_str.is_null() {
            println!("{}", CString::from_raw(leet_str).to_str().unwrap());
        }
    }

    // Free the allocated memory
    unsafe {
        if !leet_str.is_null() {
            libc::free(leet_str as *mut libc::c_void);
        }
    }
}