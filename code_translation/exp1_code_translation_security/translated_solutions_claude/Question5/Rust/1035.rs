use std::ffi::CString;
use std::os::raw::c_char;

fn e10(x: i32) -> i32 {
    let mut v = 1;
    for _i in 0..x {
        v *= 10;
    }
    v
}

fn signum(x: i32) -> i32 {
    if x < 0 {
        return -1;
    }
    if x == 0 {
        return 0;
    }
    1
}

fn abs_custom(x: i32) -> i32 {
    x * signum(x)
}

fn int_to_str(x: i32) -> *const c_char {
    static mut OUT: [u8; 17] = [0; 17]; // 16 + 1 for null terminator
    let mut exp_offset = 0;
    
    unsafe {
        for i in 0..16 {
            let digit = i - exp_offset;
            OUT[16 - i] = 0x30 + (abs_custom((x % e10(digit + 1)) / e10(digit)) as u8);
            
            if x % e10(digit + 1) == x {
                if x < 0 {
                    OUT[16 - (i + 1)] = b'-';
                    return OUT.as_ptr().add(16 - i - 1) as *const c_char;
                }
                return OUT.as_ptr().add(16 - i) as *const c_char;
            }
            
            if i == 2 || i == 6 || i == 10 {
                OUT[16 - i - 1] = b',';
                exp_offset += 1;
            }
        }
        OUT.as_ptr() as *const c_char
    }
}

fn main() {
    unsafe {
        let s1 = int_to_str(55);
        let s2 = int_to_str(12345);
        let s3 = int_to_str(-55);
        let s4 = int_to_str(-123456789);
        
        println!("{}", std::ffi::CStr::from_ptr(s1).to_str().unwrap());
        println!("{}", std::ffi::CStr::from_ptr(s2).to_str().unwrap());
        println!("{}", std::ffi::CStr::from_ptr(s3).to_str().unwrap());
        println!("{}", std::ffi::CStr::from_ptr(s4).to_str().unwrap());
    }
}