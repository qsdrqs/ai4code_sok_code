use std::fmt::Write;

fn convert(num: i32) -> char {
    let mut buf = String::new();
    write!(&mut buf, "{}", num).unwrap(); // Write the number into the buffer
    println!("{}", buf); // Print the buffer
    buf.chars().next().unwrap_or('\0') // Return the first character of the buffer, or '\0' if empty
}

fn main() {
    println!("{}", convert(100));
}