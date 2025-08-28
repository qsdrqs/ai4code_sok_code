fn num_to_string(num: i32) -> String {
    format!("{}", num).replace(" ", "").replace(":", "").replace(";", "").replace(".", ",")
    // However, Rust's format! macro already handles commas for us.
    // So we can simply use:
    format!("{:,}", num)
}

fn main() {
    println!("{}", num_to_string(-5305000));
}