use std::ffi::CString;
use std::os::raw::c_char;

fn convert_to_string(num: i32) -> *mut c_char {
    // Convert number to string
    let string = format!("{}", num);
    let space = string.len();
    
    // Calculate number of commas (this logic matches the C version)
    let num_of_commas = ((string.len() as f64) / 3.0).round() as usize;
    println!("{}", num_of_commas);
    
    // Create new string with space for commas
    let mut new_string = vec![0u8; space + 1 + num_of_commas];
    let string_bytes = string.as_bytes();
    
    // Copy characters and add commas (replicating the C logic exactly)
    for i in 0..(string.len() + 1 + num_of_commas) {
        if i < string_bytes.len() {
            new_string[i] = string_bytes[i];
            print!("{}", string_bytes[i] as char);
        }
        
        if i % 3 == 0 && i != string.len() + 1 + num_of_commas {
            print!("s");
            if i + 1 < new_string.len() {
                new_string[i + 1] = b',';
            }
        }
    }
    
    // Convert to C-style string
    let c_string = CString::new(new_string).unwrap_or_else(|_| CString::new("").unwrap());
    c_string.into_raw()
}

fn main() {
    unsafe {
        let result = convert_to_string(2000);
        let c_str = std::ffi::CStr::from_ptr(result);
        if let Ok(rust_str) = c_str.to_str() {
            print!("{}", rust_str);
        }
        // Note: In a real application, you should free the memory
        // CString::from_raw(result);
    }
}