// Cargo.toml dependencies:
// [dependencies]
// num-format = "0.4"

use num_format::{Locale, ToFormattedString};

fn print_number(num: i32) {
    println!("{}", num.to_formatted_string(&Locale::en));
}

fn main() {
    print_number(7000000);
}