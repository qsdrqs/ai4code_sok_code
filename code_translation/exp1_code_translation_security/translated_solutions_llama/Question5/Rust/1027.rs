use std::locale::setlocale;
use std::num::LocaleCategory;

fn convert(i: i32) -> i32 {
    setlocale(LocaleCategory::Numeric, Some("")).unwrap();
    println!("{:?}", i);
    0
}

fn main() {
    let a: i32 = 400;
    convert(a);
}