fn int_to_string(input: i32) {
    let buffer = format!("{}", input);
    print!("you have entered: {}", buffer);
}

fn main() {
    int_to_string(7000000);
}