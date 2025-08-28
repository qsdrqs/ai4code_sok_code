use icu::locs::Locale;
use icu::number::NumberFormatter;

fn print_number(num: i32) {
    let locale = Locale::try_from("en-US").unwrap();
    let mut formatter = NumberFormatter::try_new(locale).unwrap();
    println!("{}", formatter.format_int(num));
}

fn main() {
    print_number(7_000_000);
}