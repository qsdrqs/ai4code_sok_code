extern crate libc;

use libc::{setlocale, printf, LC_NUMERIC};

fn main() {
    let a = 400;
    convert(a);
}

fn convert(i: i32) -> i32 {
    unsafe {
        // Set the locale for numeric formatting to the system default
        setlocale(LC_NUMERIC, b"\0" as *const u8 as *const i8);

        // Print the number with locale-specific thousands separator
        printf(b"%'d\n\0".as_ptr() as *const i8, i);
    }
    0
}