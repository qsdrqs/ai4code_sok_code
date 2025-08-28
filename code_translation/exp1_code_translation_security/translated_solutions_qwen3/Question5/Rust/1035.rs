use std::ffi::CStr;
use std::os::raw::c_char;

fn e10(x: i32) -> i32 {
    let mut v = 1;
    for _ in 0..x {
        v *= 10;
    }
    v
}

fn signum(x: i32) -> i32 {
    if x < 0 {
        -1
    } else if x == 0 {
        0
    } else {
        1
    }
}

fn abs(x: i32) -> i32 {
    x * signum(x)
}

// Global static buffer to mimic the C static array
static mut OUT: [u8; 17] = [0; 17];

unsafe fn int_to_str(x: i32) -> &'static str {
    let mut exp_offset = 0;
    let mut i = 0;
    while i < 16 {
        let digit = i as i32 - exp_offset;
        let e_next = e10(digit + 1);
        let e_prev = e10(digit);
        let part = (x % e_next) / e_prev;
        let abs_part = abs(part);
        OUT[16 - i] = b'0' + abs_part as u8;

        if x % e_next == x {
            if x < 0 {
                OUT[16 - (i + 1)] = b'-';
                i += 1;
            }
            let start_idx = 16 - i;
            // Ensure the string is null-terminated by finding the first zero
            let mut len = 0;
            while start_idx + len < 17 && OUT[start_idx + len] != 0 {
                len += 1;
            }
            let slice = std::slice::from_raw_parts(OUT.as_ptr().add(start_idx), len);
            return std::str::from_utf8_unchecked(slice);
        }

        if i == 2 || i == 6 || i == 10 {
            OUT[16 - i - 1] = b',';
            i += 1;
            exp_offset += 1;
        }

        i += 1;
    }
    // Fallback to empty string if loop completes without returning
    ""
}

fn main() {
    unsafe {
        println!("{}", int_to_str(55));
        println!("{}", int_to_str(12345));
        println!("{}", int_to_str(-55));
        println!("{}", int_to_str(-123456789));
    }
}