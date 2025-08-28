use libc::{c_char, c_int, malloc, setlocale, LC_NUMERIC, sprintf};

#[no_mangle]
pub extern "C" fn sigNumToStr(num: c_int) -> *mut c_char {
    unsafe {
        // Set the locale for numeric formatting to the system default
        setlocale(LC_NUMERIC, b"\0" as *const u8 as *const c_char);

        // Allocate buffer: 4 times the size of an int
        let buffer = malloc(4 * std::mem::size_of::<c_int>()) as *mut c_char;

        // Format the number with thousand separators using the locale
        sprintf(buffer, b"%'d\0" as *const u8 as *const c_char, num);

        buffer
    }
}