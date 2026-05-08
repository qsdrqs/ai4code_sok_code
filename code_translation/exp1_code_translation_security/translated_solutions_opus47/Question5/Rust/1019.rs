fn string_rep(num: i32) -> String {
    // In Rust, String is already heap-allocated and manages its own capacity,
    // so there's no need to pre-calculate size with log10 like in C.
    format!("{}", num)
}

fn main() {
    let leet = 1337;
    let leet_str = string_rep(leet);
    print!("{}", leet_str);
    // String is automatically freed when leet_str goes out of scope (RAII)
}