use std::fmt::Write;

/// Convert an `i32` to a `String` (similar to the C `convert` function).
fn convert(num: i32) -> String {
    // In C we needed to allocate memory manually and use `snprintf`.  
    // In Rust we can simply use the standard `to_string()` method:
    num.to_string()

    // If you prefer to mimic `snprintf` more closely:
    //
    // let mut buf = String::new();
    // write!(&mut buf, "{}", num).unwrap();
    // buf
}

fn main() {
    let ans = convert(700_000);
    println!("{}", ans); // prints: 700000
}