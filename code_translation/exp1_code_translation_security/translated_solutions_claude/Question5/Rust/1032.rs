use std::alloc::{alloc, dealloc, Layout};
use std::ptr;

unsafe fn int_to_string(num: i32) -> *mut i8 {
    let mut len = 0;
    let mut temp = num;
    while temp != 0 {
        temp /= 10;
        len += 1;
    }
    
    let layout = Layout::from_size_align_unchecked(len + 1, 1);
    let str_ptr = alloc(layout) as *mut i8;
    
    *str_ptr.add(len) = 0; // null terminator
    
    let mut num_mut = num;
    for i in (0..len).rev() {
        *str_ptr.add(i) = ((num_mut % 10) as u8 + b'0') as i8;
        num_mut /= 10;
    }
    
    str_ptr
}

unsafe fn strlen(s: *const i8) -> usize {
    let mut len = 0;
    while *s.add(len) != 0 {
        len += 1;
    }
    len
}

unsafe fn int_to_string_with_commas(num: i32) -> *mut i8 {
    let str_ptr = int_to_string(num);
    let len = strlen(str_ptr);
    let comma_count = (len - 1) / 3;
    
    let layout = Layout::from_size_align_unchecked(len + comma_count + 1, 1);
    let new_str = alloc(layout) as *mut i8;
    
    *new_str.add(len + comma_count) = 0; // null terminator
    
    let mut j = 0;
    let mut k = 0;
    for i in 0..(len + comma_count) {
        if (len - j) % 3 == 0 && k != 1 && i != 0 {
            *new_str.add(i) = b',' as i8;
            k = 1;
        } else {
            *new_str.add(i) = *str_ptr.add(j);
            j += 1;
            if k == 1 {
                k = 0;
            }
        }
    }
    
    let layout = Layout::from_size_align_unchecked(len + 1, 1);
    dealloc(str_ptr as *mut u8, layout);
    
    new_str
}

unsafe fn printf(s: *const i8) {
    let mut len = 0;
    while *s.add(len) != 0 {
        len += 1;
    }
    
    let slice = std::slice::from_raw_parts(s as *const u8, len);
    let string = String::from_utf8_lossy(slice);
    print!("{}", string);
}

fn main() {
    unsafe {
        let num = 7654321;
        let str_ptr = int_to_string_with_commas(num);
        printf(str_ptr);
        println!(); // for the newline
        
        // Calculate the length to properly deallocate
        let len = strlen(str_ptr);
        let layout = Layout::from_size_align_unchecked(len + 1, 1);
        dealloc(str_ptr as *mut u8, layout);
    }
}