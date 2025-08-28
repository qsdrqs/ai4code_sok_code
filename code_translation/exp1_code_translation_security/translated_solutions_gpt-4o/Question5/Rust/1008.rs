use std::env;
use std::ffi::CString;
use std::os::raw::c_char;
use libc::{setlocale, LC_NUMERIC};

fn print_number(num: i32) {
    // Set the locale to the user's environment
    let locale = CString::new("").unwrap();
    unsafe {
        setlocale(LC_NUMERIC, locale.as_ptr() as *const c_char);
    }

    // Format the number with thousands separators
    println!("{:?}", num.to_string());
}

fn main() {
    print_number(7_000_000);
}