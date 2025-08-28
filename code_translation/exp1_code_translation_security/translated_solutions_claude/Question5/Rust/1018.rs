use std::alloc::{alloc, Layout};
use std::ptr;

fn change(input: i32) -> *mut i8 {
    let mut count = 0;
    let mut tmp = input;
    
    loop {
        tmp /= 10;
        count += 1;
        if tmp == 0 {
            break;
        }
    }
    
    let num = count + count / 3;
    
    // Allocate memory similar to malloc
    let layout = Layout::from_size_align(num + 2, 1).unwrap();
    let result = unsafe { alloc(layout) as *mut i8 };
    
    let mut mid = 0;
    let mut input_mut = input;
    println!("{}", num);
    
    let mut i = (num - 1) as isize;
    while i >= 0 {
        if mid == 3 {
            mid = 0;
            unsafe {
                *result.offset(i) = b',' as i8;
            }
            i -= 1;
        }
        if i >= 0 {
            unsafe {
                *result.offset(i) = (input_mut % 10 + ('0' as i32)) as i8;
            }
            input_mut /= 10;
            mid += 1;
        }
        i -= 1;
    }
    
    // Null terminate the string
    unsafe {
        *result.offset((num + 1) as isize) = 0;
    }
    
    result
}

fn main() {
    let snum = change(700000000);
    
    // Convert the raw pointer to a CStr for printing
    unsafe {
        let c_str = std::ffi::CStr::from_ptr(snum);
        println!("{}", c_str.to_str().unwrap());
        
        // Free the allocated memory
        let layout = Layout::from_size_align(std::ffi::CStr::from_ptr(snum).to_bytes().len() + 2, 1).unwrap();
        std::alloc::dealloc(snum as *mut u8, layout);
    }
}