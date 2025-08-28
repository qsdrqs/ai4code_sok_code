use libc::{c_char, c_int, malloc, sprintf, free};

// Link to the math library for log10
#[link(name = "m")]
extern "C" {}

/// Translates the original C function `string_rep(int num)` into Rust.
/// Returns a pointer to a pointer to a C string (`*mut *mut c_char`).
fn string_rep(num: c_int) -> *mut *mut c_char {
    // Compute log10(num) and cast to usize
    let log_val = unsafe { libc::log10(num as f64) };
    let size = log_val as i32 as usize;

    // Allocate memory for a char** (pointer to a pointer to char)
    let str_ptr = unsafe { malloc(size) } as *mut *mut c_char;

    // Write the number into the buffer pointed to by *str_ptr
    // This is undefined behavior because *str_ptr is uninitialized
    unsafe {
        sprintf(*str_ptr, b"%d\0".as_ptr() as *const c_char, num);
    }

    str_ptr
}

fn main() {
    let leet = 1337;
    let leet_str = string_rep(leet);

    // Print the string and free the memory
    unsafe {
        libc::printf(b"%s\0".as_ptr() as *const c_char, *leet_str);
        libc::free(leet_str as *mut _);
    }
}