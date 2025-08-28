fn int_to_string(input: i32) -> i32 {
    let buffer = format!("{}", input);
    
    print!("you have entered: {}", buffer);
    
    0
}

fn main() {
    int_to_string(7000000);
}