extern crate locale;
extern crate num_format;

use locale::set_locale;
use locale::category::LC_NUMERIC;
use num_format::ToFormattedString;

fn print_number(num: i32) {
    unsafe {
        // Set the locale for numeric formatting to the system default
        set_locale(LC_NUMERIC, "");
    }
    // Format the number with the current locale's thousand separator
    let formatted = num.to_formatted_string();
    println!("{}", formatted);
}

fn main() {
    print_number(7000000);
}