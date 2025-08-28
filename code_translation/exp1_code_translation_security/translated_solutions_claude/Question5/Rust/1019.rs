use std::ffi::{CString, CStr};
use std::ptr;

// External dependencies (would be provided)
extern "C" {
    fn log10(x: f64) -> f64;
    fn malloc(size: usize) -> *mut std::ffi::c_void;
    fn free(ptr: *mut std::ffi::c_void);
    fn sprintf(s: *mut std::ffi::c_char, format: *const std::ffi::c_char, ...) -> std::ffi::c_int;
    fn printf(format: *const std::ffi::c_char, ...) -> std::ffi::c_int;
}

unsafe fn string_rep(num: i32) -> *mut *mut std::ffi::c_char {
    // Calculate size needed for the string representation
    // Adding extra space for null terminator and the pointer
    let digits = if num == 0 { 1 } else { (log10(num.abs() as f64) as i32) + 1 };
    let sign_space = if num < 0 { 1 } else { 0 };
    let str_size = (digits + sign_space + 1) as usize; // +1 for null terminator
    
    // Allocate memory for the pointer to char*
    let str_ptr = malloc(std::mem::size_of::<*mut std::ffi::c_char>()) as *mut *mut std::ffi::c_char;
    
    // Allocate memory for the actual string
    let str_data = malloc(str_size) as *mut std::ffi::c_char;
    
    // Store the string pointer in the allocated pointer
    *str_ptr = str_data;
    
    // Format the number into the string
    let format = CString::new("%d").unwrap();
    sprintf(str_data, format.as_ptr(), num);
    
    str_ptr
}

fn main() {
    unsafe {
        let leet = 1337;
        let leet_str = string_rep(leet);
        
        let format = CString::new("%s").unwrap();
        printf(format.as_ptr(), *leet_str);
        
        // Free the string data first, then the pointer
        free(*leet_str as *mut std::ffi::c_void);
        free(leet_str as *mut std::ffi::c_void);
    }
}