#[macro_use]
extern crate num_format;

extern crate libc;

use libc::{setlocale, LC_NUMERIC, localeconv};
use num_format::{CustomFormat, ToFormattedString};
use std::ffi::{CStr, CString};

fn main() {
    unsafe {
        // Set the numeric locale to the system default
        setlocale(LC_NUMERIC, CString::new("").unwrap().as_ptr());

        // Get the locale conversion structure
        let lconv = localeconv();

        // Extract the thousands separator
        let thousands_sep = if (*lconv).thousands_sep.is_null() {
            None
        } else {
            Some(CStr::from_ptr((*lconv).thousands_sep)
                .to_string_lossy()
                .into_owned())
        };

        // Extract the grouping rules
        let grouping = if (*lconv).grouping.is_null() {
            Vec::new()
        } else {
            let mut groups = Vec::new();
            let mut i = 0;
            loop {
                let g = *(*lconv).grouping.offset(i);
                if g == 0 {
                    break;
                }
                groups.push(g as i8);
                if g == libc::CHAR_MAX {
                    break; // No further grouping
                }
                i += 1;
            }
            groups
        };

        // Build the custom format based on locale settings
        let mut builder = CustomFormat::builder();
        if let Some(ref sep) = thousands_sep {
            builder.separator(sep);
        }
        if !grouping.is_empty() {
            builder.grouping(&grouping);
        }
        let format = builder.build().unwrap();

        // Format the number
        let num = 7000000;
        let formatted = num.to_formatted_string(&format);

        // Print the result
        println!("{}", formatted);
    }
}