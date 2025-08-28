fn num_to_string(num: i32) -> String {
    num.to_string()
}

fn main() {
    let result = num_to_string(7_000_000);
    println!("{}", result);
}