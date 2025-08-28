use std::ffi::CString;
use std::os::raw::c_char;

fn sig_num_to_str(num: i32) -> *mut c_char {
    // Format number with thousands separator using Rust's formatting
    let formatted = format!("{:}", num.to_string()
        .chars()
        .rev()
        .collect::<Vec<_>>()
        .chunks(3)
        .map(|chunk| chunk.iter().collect::<String>())
        .collect::<Vec<_>>()
        .join(",")
        .chars()
        .rev()
        .collect::<String>());
    
    // Convert to C string and leak the memory to return a raw pointer
    let c_string = CString::new(formatted).unwrap();
    c_string.into_raw()
}

// Or using a crate like `num-format` for proper locale-aware formatting:
use num_format::{Locale, ToFormattedString};

fn sig_num_to_str_with_locale(num: i32) -> *mut c_char {
    let formatted = num.to_formatted_string(&Locale::en);
    let c_string = CString::new(formatted).unwrap();
    c_string.into_raw()
}