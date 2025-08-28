use std::alloc::{alloc, Layout};
use std::ptr;

/*
Function repr

Takes in a signed integer and returns the string representation of that integer.
*/

const MAX_DIGITS: usize = 100;

fn repr(n: i32) -> String {
    let mut str_vec = Vec::with_capacity(MAX_DIGITS);
    let mut i = 0;
    let mut sign = 1;
    let mut n = n;
    
    if n == -2147483648 {
        // When multiplied by -1 this overflows, so handle it separately.
        return "-2,147,483,648".to_string();
    }
    
    if n == 0 {
        return "0".to_string();
    }
    
    if n < 0 {
        sign = -1;
        n *= -1;
    }
    
    while n > 0 {
        str_vec.push(((n % 10) as u8 + b'0') as char);
        i += 1;
        if (i + 1) % 4 == 0 && n > 10 {
            str_vec.push(',');
            i += 1;
        }
        n /= 10;
    }
    
    if sign == -1 {
        str_vec.push('-');
    }
    
    let mut result: String = str_vec.into_iter().collect();
    reverse_string(&mut result);
    result
}

/*
Function reverse

Takes in a string and reverses it.
*/

fn reverse_string(s: &mut String) {
    let bytes = unsafe { s.as_bytes_mut() };
    let mut i = 0;
    let mut j = bytes.len() - 1;
    
    while i < j {
        bytes.swap(i, j);
        i += 1;
        j -= 1;
    }
}

fn main() {
    let n = 0;
    let str_result = repr(n);
    print!("{}", str_result);
}