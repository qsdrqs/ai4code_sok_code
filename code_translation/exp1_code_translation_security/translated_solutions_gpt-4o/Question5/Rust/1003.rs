use std::ffi::CString;

fn int2str(num: i32) -> CString {
    let buf = format!("{}", num); // Format the integer as a string
    CString::new(buf).expect("Failed to create CString") // Convert to CString
}