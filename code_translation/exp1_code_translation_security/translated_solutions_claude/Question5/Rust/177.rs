use std::alloc::{alloc, Layout};
use std::ptr;

fn int2str(num: i32) -> *mut i8 {
    // Convert number to string to get its length
    let num_str = format!("{}", num);
    let length = num_str.len();
    
    // Allocate memory for the original string
    let layout = Layout::from_size_align(length + 1, 1).unwrap();
    let str_ptr = unsafe { alloc(layout) as *mut i8 };
    
    // Copy the string into allocated memory
    unsafe {
        ptr::copy_nonoverlapping(num_str.as_ptr() as *const i8, str_ptr, length);
        *str_ptr.add(length) = 0; // null terminator
    }
    
    // Calculate new length with commas
    let newl = if length % 3 == 0 {
        length + length / 3 - 1
    } else {
        length + length / 3
    };
    
    // Allocate memory for result
    let result_layout = Layout::from_size_align(newl + 1, 1).unwrap();
    let result = unsafe { alloc(result_layout) as *mut i8 };
    
    // Fill result with commas, working backwards
    let mut counter = 0;
    let mut j = newl as isize - 1;
    
    unsafe {
        for i in (0..length as isize).rev() {
            counter += 1;
            *result.offset(j) = *str_ptr.offset(i);
            j -= 1;
            
            if counter % 3 == 0 && i > 0 {
                *result.offset(j) = b',' as i8;
                j -= 1;
            }
        }
        
        // Null terminate the result
        *result.add(newl) = 0;
    }
    
    result
}

// Helper function to demonstrate usage (since raw pointers need careful handling)
fn int2str_safe(num: i32) -> String {
    let ptr = int2str(num);
    unsafe {
        let c_str = std::ffi::CStr::from_ptr(ptr);
        let result = c_str.to_string_lossy().into_owned();
        
        // Free the allocated memory
        let num_str = format!("{}", num);
        let length = num_str.len();
        let newl = if length % 3 == 0 {
            length + length / 3 - 1
        } else {
            length + length / 3
        };
        
        std::alloc::dealloc(
            ptr as *mut u8,
            Layout::from_size_align(newl + 1, 1).unwrap()
        );
        
        result
    }
}

fn main() {
    // Example usage
    let result = int2str_safe(1234567);
    println!("{}", result);
}