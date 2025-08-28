use libc::{c_char, malloc, sprintf};

#[no_mangle]
pub unsafe extern "C" fn inttostring(x: i32) -> *mut c_char {
    // Allocate 12 bytes using C's malloc
    let buffer = malloc(12) as *mut c_char;

    // Format the integer into the buffer using C's sprintf
    sprintf(buffer, b"%d\0".as_ptr() as *const c_char, x);

    // Return the pointer to the allocated buffer
    buffer
}