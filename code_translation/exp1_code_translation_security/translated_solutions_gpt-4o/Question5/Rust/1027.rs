use std::locale::{setlocale, LocaleCategory, LC_NUMERIC};
use std::ffi::CString;

fn convert(i: i32) -> i32 {
    // Set the locale for numeric formatting
    setlocale(LocaleCategory::Numeric, Some(""));

    // Format the number with thousands separator
    let formatted = format!("{:?}", i);
    println!("{}", formatted);

    0
}

fn main() {
    let a = 400;
    convert(a);
}