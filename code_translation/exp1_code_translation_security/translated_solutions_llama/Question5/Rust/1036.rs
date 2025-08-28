fn num_digits(n: i32) -> usize {
    if n == 0 {
        return 1;
    }
    let mut cnt = 0;
    let mut n = n.abs(); // handle negative numbers
    while n != 0 {
        n = n / 10;
        cnt += 1;
    }
    cnt
}

fn return_string(num: i32) -> String {
    let num_digits = num_digits(num);
    let mut ret = String::new();

    let mut num_str = num.to_string();
    let mut i = num_str.len();
    while i > 0 {
        if i % 3 == 0 && i != num_str.len() {
            ret.push(',');
        }
        ret.push(num_str.chars().nth(i - 1).unwrap());
        i -= 1;
    }

    ret.chars().rev().collect()
}

fn main() {
    let num = 7000000;
    let str = return_string(num);
    println!("{}", str);
}