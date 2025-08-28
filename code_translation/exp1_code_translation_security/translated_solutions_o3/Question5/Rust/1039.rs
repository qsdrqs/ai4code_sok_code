fn get_length(mut num: i32) -> usize {
    // Replicates the C while-loop that counts decimal digits.
    let mut len = 0;
    while num > 0 {
        num /= 10;
        len += 1;
    }
    len
}

fn convert_to_string(_num: i32) -> String {
    // The C code tried to put "test" into a local array and return it.
    // In Rust we can just build and return an owned `String`.
    "test".to_string()
}

fn main() {
    let num = 7_000_000;
    println!("{}", get_length(num));
    print!("{}", convert_to_string(5));
}