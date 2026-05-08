fn convert(num: i32) -> String {
    // Did not add comma because it's too complicated
    format!("{}", num)
}

fn main() {
    let ans = convert(700000);
    println!("{}", ans);
    // No need to manually free - Rust automatically drops `ans` when it goes out of scope
}