use std::ffi::CString;
use std::os::raw::c_char;

// Translating convertInt function (broken and unsafe)
#[no_mangle]
pub extern "C" fn convert_int(mut input: i32) -> *mut c_char {
    // This function attempts to replicate the original C code's behavior, which is undefined.
    // The original C code returns a pointer to a local stack-allocated buffer, which is invalid.
    // To mimic this in Rust, we allocate a buffer on the heap and leak it to prevent deallocation.
    // However, the loop logic is flawed as it relies on uninitialized data.

    let mut buffer = Vec::with_capacity(9);
    let buf_ptr = buffer.as_mut_ptr();
    std::mem::forget(buffer); // Prevent deallocation

    let mut i = 0;
    loop {
        // This loop condition checks for '\0' in the buffer, but the buffer is uninitialized.
        // This leads to undefined behavior, similar to the original C code.
        unsafe {
            if *buf_ptr.add(i) == 0 {
                break;
            }
            *buf_ptr.add(i) = (input % 10 + 48) as c_char;
            input /= 10;
            i += 1;
        }
    }

    buf_ptr as *mut c_char
}

// Translating convertIntAI function
#[no_mangle]
pub extern "C" fn convert_int_ai(input: i32) -> *mut c_char {
    // Create a CString from the integer and leak it to return a raw pointer
    CString::new(format!("{}", input)).unwrap().into_raw()
}

fn main() {
    // Call convert_int_ai and print the result
    let output = unsafe { convert_int_ai(7000000) };
    if !output.is_null() {
        unsafe {
            // Convert the *mut c_char to a &CStr and then to a &str
            let c_str = std::ffi::CStr::from_ptr(output);
            let rust_str = c_str.to_str().unwrap();
            println!("{}", rust_str);
        }
    }
    // Note: The memory allocated by convert_int_ai is not freed here to match the original C code's behavior.
    // In a real application, you should free the memory using `CString::from_raw(output)` when done.
}