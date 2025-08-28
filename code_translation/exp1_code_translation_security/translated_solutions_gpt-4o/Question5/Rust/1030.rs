use std::env;
use std::fmt::Write;
use num_format::{Locale, ToFormattedString};

fn main() {
    let num = 7_000_000; // -703840;
    
    // Format the number with a locale (e.g., en_US for thousands separator)
    let formatted_num = num.to_formatted_string(&Locale::en);

    // Print the formatted number
    println!("{}", formatted_num);
}