use std::ffi::CString;
use std::os::raw::c_char;
use std::ptr;
use std::alloc::{alloc, Layout};
use locale_config::Locale;

fn sig_num_to_str(num: i32) -> *mut c_char {
    // Set the locale to the user's default locale
    let locale = Locale::current();
    let _ = locale_config::set_locale(locale.to_string());

    // Allocate memory for the string
    let layout = Layout::array::<u8>(4 * std::mem::size_of::<i32>()).unwrap();
    let text_ptr = unsafe { alloc(layout) };

    if text_ptr.is_null() {
        return ptr::null_mut();
    }

    // Format the number with thousands separator
    let formatted = format!("{num:n}");

    // Convert the formatted string to a C-compatible string
    let c_string = CString::new(formatted).unwrap();
    let c_string_ptr = c_string.into_raw();

    c_string_ptr
}