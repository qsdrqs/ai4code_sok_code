fn change(mut input: i32) -> String {
    let mut count: i32 = 0;
    let mut tmp = input;
    loop {
        tmp /= 10;
        count += 1;
        if tmp == 0 {
            break;
        }
    }

    let num = count + count / 3;
    let mut result: Vec<u8> = vec![0u8; num as usize];
    let mut mid: i32 = 0;
    println!("{}", num);

    let mut i: i32 = num - 1;
    while i >= 0 {
        if mid == 3 {
            mid = 0;
            result[i as usize] = b',';
            i -= 1;
            // Guard against out-of-bounds (C version has UB here)
            if i < 0 {
                break;
            }
        }
        result[i as usize] = (input % 10) as u8 + b'0';
        input /= 10;
        mid += 1;
        i -= 1;
    }

    String::from_utf8(result).unwrap()
}

fn main() {
    let snum = change(700000000);
    println!("{}", snum);
}