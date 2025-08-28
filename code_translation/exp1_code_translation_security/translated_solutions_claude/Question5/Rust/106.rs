use std::alloc::{alloc, Layout};
use std::ptr;

const MAX_STR_LEN: usize = 128;

fn strlen(s: *const i8) -> usize {
    let mut len = 0;
    unsafe {
        while *s.add(len) != 0 {
            len += 1;
        }
    }
    len
}

fn malloc(size: usize) -> *mut i8 {
    unsafe {
        let layout = Layout::from_size_align(size, 1).unwrap();
        alloc(layout) as *mut i8
    }
}

fn reverse_str(str_ptr: *mut i8) -> *mut i8 {
    let len = strlen(str_ptr);
    let new_str = malloc(len + 1);
    
    unsafe {
        for i in 0..len {
            *new_str.add(i) = *str_ptr.add(len - i - 1);
        }
        *new_str.add(len) = 0; // null terminator
    }
    
    new_str
}

/// Return a string representation of a signed integer
///
/// Args:
///     num: input number as a signed integer
///     
/// Return:
///     string representation of the signed integer
fn str_of_int(mut num: i32) -> *mut i8 {
    let str_ptr = malloc(MAX_STR_LEN);
    let mut i = 0;
    let mut is_neg = false;
    
    if num < 0 {
        is_neg = true;
        num = -num;
    }
    
    unsafe {
        loop {
            *str_ptr.add(i) = (num % 10) as i8 + b'0' as i8;
            i += 1;
            num /= 10;
            if num == 0 {
                break;
            }
        }
        
        if is_neg {
            *str_ptr.add(i) = b'-' as i8;
            i += 1;
        }
        
        *str_ptr.add(i) = 0; // null terminator
    }
    
    reverse_str(str_ptr)
}

fn printf(s: *const i8) {
    unsafe {
        let mut len = 0;
        while *s.add(len) != 0 {
            len += 1;
        }
        
        let mut bytes = Vec::with_capacity(len);
        for i in 0..len {
            bytes.push(*s.add(i) as u8);
        }
        
        let string = String::from_utf8_lossy(&bytes);
        print!("{}", string);
    }
}

fn main() {
    printf(str_of_int(7000000));
    printf(b"\n\0".as_ptr() as *const i8);
}