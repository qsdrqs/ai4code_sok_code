fn int_to_string(num: i32) -> String {
    let mut s = Vec::new();
    let mut temp = num;
    let mut count = 0;

    while temp != 0 {
        let digit = temp % 10;
        s.push((digit as u8 + b'0') as char);
        temp /= 10;
        count += 1;

        if count == 3 && temp != 0 {
            s.push(',');
            count = 0;
        }
    }

    s.reverse();

    String::from_iter(s)
}

fn main() {
    let result = int_to_string(7000000);
    println!("{}", result);
}