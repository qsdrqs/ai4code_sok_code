fn int_to_string(num: i32) -> String {
    let mut s: Vec<u8> = vec![0u8; 100];
    let mut i: usize = 0;
    let mut j: usize = 0;
    let mut temp = num;
    let mut count = 0;

    // Build digits (and commas) in reverse order
    while temp != 0 {
        s[i] = (temp % 10) as u8 + b'0';
        temp = temp / 10;
        i += 1;
        count += 1;
        if count == 3 && temp != 0 {
            s[i] = b',';
            i += 1;
            count = 0;
        }
    }

    // Trim the buffer to the actual used length
    s.truncate(i);

    // Reverse the string in place
    if i > 0 {
        let mut end = i - 1;
        while j < end {
            s.swap(j, end);
            j += 1;
            end -= 1;
        }
    }

    String::from_utf8(s).unwrap()
}

fn main() {
    println!("{}", int_to_string(7000000));
}