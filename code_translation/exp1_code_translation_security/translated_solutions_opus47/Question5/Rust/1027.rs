use num_format::{Locale, ToFormattedString};

fn convert(i: i32) -> i32 {
    println!("{}", i.to_formatted_string(&Locale::en));
    0
}

fn main() {
    let a: i32 = 400;
    convert(a);
}