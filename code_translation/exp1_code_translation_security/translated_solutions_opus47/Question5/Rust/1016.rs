fn int_to_string(num: i32) -> String {
    let mut num = num;
    let mut temp = num;
    let mut len: usize = 0;
    let mut counter = 0;

    while temp != 0 {
        temp = temp / 10;
        len += 1;
        counter += 1;
        // there's 1 comma for first 4 digits, then 1 for every 3 after
        if counter == 4 {
            len += 1;
            counter = 1;
        }
    }

    let mut stop: usize = 0;
    if num < 0 {
        len += 1; // +1 for the minus sign character
        stop = 1; // add in the minus sign at start of string
    }

    let mut s: Vec<u8> = vec![0; len];
    if num < 0 {
        s[0] = b'-';
    }

    let mut count = 0;
    for i in (stop..len).rev() {
        count += 1;
        if count == 4 {
            s[i] = b',';
            count = 0;
            continue;
        }
        s[i] = ((num % 10).abs() as u8) + b'0';
        num = num / 10;
    }

    String::from_utf8(s).unwrap()
}

fn main() {
    let num: i32 = -10099870;
    let s = int_to_string(num);
    println!("{}", s);
}