use std::ffi::CStr;
use std::os::raw::{c_char, c_int};
use std::ptr;

extern "C" {
    fn malloc(size: usize) -> *mut c_char;
}

fn convert_to_string(num: i32) -> *mut c_char {
    // Convert number to string to determine length
    let temp = num.to_string();
    let space = temp.len();
    
    // Allocate and copy the original string
    let original = CString::new(temp).expect("CString::new failed");
    let string = unsafe {
        let ptr = malloc(space + 1);
        ptr::copy_nonoverlapping(original.as_ptr(), ptr, space + 1);
        ptr
    };
    
    // Calculate number of commas
    let len = unsafe { CStr::from_ptr(string).to_bytes().len() };
    let num_commas = len / 3;
    unsafe {
        println!("{}", num_commas);
    }
    
    // Allocate new string with space for commas
    let new_string_size = len + 1 + num_commas;
    let new_string = unsafe {
        let ptr = malloc(new_string_size);
        ptr::write_bytes(ptr, 0, new_string_size);
        ptr
    };
    
    // Copy characters and insert commas
    for i in 0..new_string_size {
        unsafe {
            // Copy character from original string
            if i < len + 1 {
                *new_string.offset(i as isize) = *string.offset(i as isize);
            } else {
                *new_string.offset(i as isize) = 0;
            }
            
            // Print ASCII value of the character
            print!("{}", *new_string.offset(i as isize) as u8 as c_int);
            
            // Insert comma if needed
            if i % 3 == 0 && i != new_string_size {
                print!("s");
                if i + 1 < new_string_size {
                    *new_string.offset((i + 1) as isize) = ',' as c_char;
                }
            }
        }
    }
    
    // Leak the original string to simulate C's memory leak
    new_string
}

fn main() {
    let result = convert_to_string(2000);
    unsafe {
        println!("{}", CStr::from_ptr(result).to_str().unwrap());
    }
}