use std::ffi::CString;

fn int_to_string(x: i32) -> CString {
    CString::new(x.to_string()).expect("Failed to create CString")
}