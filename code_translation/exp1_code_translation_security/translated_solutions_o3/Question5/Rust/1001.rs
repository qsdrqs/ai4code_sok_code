use std::ffi::CString;
use std::os::raw::c_char;

/// Equivalent of the C function
///     char* inttostring(int x);
///
/// Returns a newly-allocated, NUL-terminated C string that the caller must
/// eventually free with `CString::from_raw(ptr)`.
#[no_mangle]               // keep the original symbol name
pub extern "C" fn inttostring(x: i32) -> *mut c_char {
    // Convert the integer to a Rust `String`
    let tmp = x.to_string();

    // Turn it into a NUL-terminated C string
    // (safe because `to_string()` never contains interior NULs)
    let c_string = CString::new(tmp).expect("CString::new failed");

    // Give ownership of the allocation to the caller
    c_string.into_raw()
}