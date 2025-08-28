use std::ffi::CString;
use std::os::raw::c_char;

#[no_mangle]
pub extern "C" fn int2str(num: i32) -> *const c_char {
    let s = format!("{}", num);
    let c_string = CString::new(s).unwrap();
    c_string.into_raw()
}