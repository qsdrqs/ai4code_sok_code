use std::ffi::CString;
use std::os::raw::c_char;
use std::ptr;

fn convert(num: i32) -> *mut c_char {
    // Did not add comma because it's too complicated
    
    let str_repr = format!("{}", num);
    let len = str_repr.len();
    
    // Allocate memory for 3 bytes (matching the original C code)
    let str_ptr = unsafe {
        libc::malloc(3) as *mut c_char
    };
    
    if str_ptr.is_null() {
        println!("failed");
        return ptr::null_mut();
    }
    
    // Convert Rust string to C string and copy to allocated memory
    let c_string = CString::new(str_repr).unwrap();
    unsafe {
        libc::strncpy(str_ptr, c_string.as_ptr(), len + 1);
    }
    
    str_ptr
}

fn main() {
    let ans = convert(700000);
    if !ans.is_null() {
        unsafe {
            let c_str = std::ffi::CStr::from_ptr(ans);
            if let Ok(rust_str) = c_str.to_str() {
                println!("{}", rust_str);
            }
            libc::free(ans as *mut libc::c_void);
        }
    }
}