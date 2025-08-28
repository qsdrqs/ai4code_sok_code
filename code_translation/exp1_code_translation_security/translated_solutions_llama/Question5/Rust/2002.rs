use std::alloc::{alloc, dealloc, Layout};
use std::ffi::CStr;
use std::fmt;

fn sig_num_to_str(num: i32) -> *mut i8 {
    let formatted_num = format!("{:,}", num);
    let c_str = formatted_num.as_bytes_with_nul();
    let layout = Layout::from_size_align(c_str.len(), 1).unwrap();
    let ptr = alloc(layout) as *mut i8;
    unsafe {
        std::ptr::copy(c_str.as_ptr() as *const i8, ptr, c_str.len());
    }
    ptr
}

fn main() {
    let num = 1234567;
    let formatted_str = sig_num_to_str(num);
    unsafe {
        println!("{}", CStr::from_ptr(formatted_str).to_str().unwrap());
        dealloc(formatted_str as *mut u8, Layout::from_size_align(20, 1).unwrap());
    }
}