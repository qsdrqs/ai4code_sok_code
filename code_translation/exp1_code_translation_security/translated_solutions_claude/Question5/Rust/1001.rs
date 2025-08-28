use std::ffi::CString;

fn inttostring(x: i32) -> *mut i8 {
    let s = format!("{}", x);
    let c_string = CString::new(s).unwrap();
    c_string.into_raw()
}