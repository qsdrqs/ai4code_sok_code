use std::ffi::CString;
use std::os::raw::{c_char, c_int};

extern "C" {
    fn setlocale(category: c_int, locale: *const c_char) -> *mut c_char;
    fn sprintf(s: *mut c_char, format: *const c_char, ...) -> c_int;
    fn printf(format: *const c_char, ...) -> c_int;
}

const LC_NUMERIC: c_int = 1;

fn main() {
    let num: c_int = 7000000; // -703840;
    let mut snum = [0i8; 20];
    
    unsafe {
        let locale_str = CString::new("").unwrap();
        setlocale(LC_NUMERIC, locale_str.as_ptr());
        
        let format_str = CString::new("%'d").unwrap();
        sprintf(snum.as_mut_ptr(), format_str.as_ptr(), num);
        
        printf(snum.as_ptr());
    }
}