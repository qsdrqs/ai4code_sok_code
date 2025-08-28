use std::fmt::Write;

fn int_to_string(input: i32) -> String {
    let mut buffer = String::new();

    // Write the integer into the buffer
    write!(&mut buffer, "{}", input).unwrap();

    println!("you have entered: {}", buffer);

    buffer
}

fn main() {
    int_to_string(7000000);
}