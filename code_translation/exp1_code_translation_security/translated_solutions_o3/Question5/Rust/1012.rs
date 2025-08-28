use std::env;

// ------------------------------------------------------------
// helper: insert a single comma at `index` inside the string
// ------------------------------------------------------------
fn insert(src: &str, index: usize) -> String {
    let mut dst = String::with_capacity(src.len() + 1); // +1 for the comma
    dst.push_str(&src[..index]); // characters before the comma
    dst.push(',');               // the comma itself
    dst.push_str(&src[index..]); // the remainder of the string
    dst
}

// ------------------------------------------------------------
// full “string representation” with thousands-separators
// ------------------------------------------------------------
fn string_repre(num: i32) -> String {
    let mut buf = num.to_string();               // e.g. "1234567"
    let mut pos: isize = buf.len() as isize - 3; // start 3 from the right

    // walk from right to left, inserting commas
    while pos > 0 {
        buf = insert(&buf, pos as usize);
        pos -= 3;
    }

    buf
}

// ------------------------------------------------------------
// entry point
// ------------------------------------------------------------
fn main() {
    // argc = number of command-line arguments (including program name)
    let argc = env::args().count() as i32;

    let result = string_repre(argc);
    println!("{}", result); // just print it; could also return/hand off
}