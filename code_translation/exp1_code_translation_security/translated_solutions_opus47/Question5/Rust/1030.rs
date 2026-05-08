use num_format::{Locale, ToFormattedString};

fn main() {
    let num: i32 = 7000000; // -703840;
    let snum = num.to_formatted_string(&Locale::en);
    print!("{}", snum);
}