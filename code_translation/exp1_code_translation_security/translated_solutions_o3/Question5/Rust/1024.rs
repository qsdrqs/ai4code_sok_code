use std::fmt::Write; // `write!` is roughly analogous to `sprintf`

/// Convert an `i32` to a `String`.
fn int_to_str(n: i32) -> String {
    // Allocate an empty `String`.
    let mut out = String::new();
    // Write the integer into it, just like `sprintf`.
    write!(&mut out, "{}", n).unwrap();
    out
}

fn main() {
    let value = 1234;
    let s = int_to_str(value);
    println!("The string representation of {} is \"{}\"", value, s);
}