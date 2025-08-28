use std::io::{self, Write};

/// Prints the decimal representation of `num` and
/// returns its first character (or '\0' if the string is empty).
fn convert(num: i32) -> char {
    let buf = num.to_string();     // equivalent to sprintf(buf, "%d", num)
    print!("{}", buf);             // prints the string
    io::stdout().flush().unwrap(); // make sure it appears before the next print

    buf.chars().next().unwrap_or('\0')
}

fn main() {
    // Mirrors `printf("%c", convert(100));` in C
    print!("{}", convert(100));
}