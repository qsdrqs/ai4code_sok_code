fn num_to_string(num: i32) -> String {
    num.to_string()        // allocate a new String containing `num`
}

fn main() {
    println!("{}", num_to_string(7_000_000));
}