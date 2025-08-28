use std::alloc::{alloc, Layout};
use std::ffi::CString;
use std::os::raw::c_char;

fn convert_int(input: i32) -> String {
    let mut output = [0u8; 9];
    let mut input = input;
    
    // Note: The original C code has a bug - it checks output[i] != '\0' 
    // but output is uninitialized. This would likely cause undefined behavior.
    // I'll implement what seems to be the intended logic:
    let mut i = 0;
    while i < 8 && input > 0 {
        output[i] = (input % 10 + 48) as u8;
        input /= 10;
        i += 1;
    }
    
    // Convert the digits to a string (they're in reverse order)
    let digits: Vec<char> = output[0..i]
        .iter()
        .rev()
        .map(|&b| b as char)
        .collect();
    
    digits.into_iter().collect()
}

fn convert_int_ai(input: i32) -> String {
    // In Rust, we can simply use format! macro or to_string()
    format!("{}", input)
    // or simply: input.to_string()
}

fn main() {
    let output = convert_int_ai(7000000);
    print!("{}", output);
}