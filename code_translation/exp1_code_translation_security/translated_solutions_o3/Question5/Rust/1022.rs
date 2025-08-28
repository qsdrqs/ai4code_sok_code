use std::io::{self, Write};

fn print_num(num: i32) {
    if num < 0 {
        print!("-");
        print_num(-num);
        return;
    }

    if num < 1_000 {
        print!("{}", num);
        return;
    }

    print_num(num / 1_000);
    print!(",{:03}", num % 1_000);
}

fn main() {
    print_num(-12_345);

    // Flush stdout so the buffered `print!` output is displayed immediately
    io::stdout().flush().unwrap();
}