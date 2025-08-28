use std::fmt::Write;

fn num_digits(n: i32) -> usize {
    if n == 0 {
        return 1;
    }
    let mut cnt = 0;
    let mut n = n;
    while n != 0 {
        n /= 10;
        cnt += 1;
    }
    cnt
}

fn return_string(num: i32) -> String {
    let digits = num_digits(num);
    let mut str = num.to_string(); // Convert the number to a string
    let mut ret = String::new();

    let mut i = 0;
    let mut comma_counter = 0;

    while i < digits {
        if comma_counter == 3 {
            ret.push(','); // Add a comma every third index
            comma_counter = 0;
        }
        ret.push(str.chars().nth(i).unwrap()); // Push the current character
        comma_counter += 1;
        i += 1;
    }

    ret
}

fn main() {
    let num = 7_000_000; // Use underscores for readability in large numbers
    let str = return_string(num);
    println!("{}", str);
}