fn print_num(num: i32) {
    if num < 0 {
        print!("-");
        print_num(-num);
        return;
    }
    if num < 1000 {
        print!("{}", num);
        return;
    }
    print_num(num / 1000);
    print!(",{:03}", num % 1000);
}

fn main() {
    print_num(-12345);
}