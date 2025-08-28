fn int_to_string(mut num: i32) -> String {
    let mut len = 0;
    let mut temp = num;
    let mut counter = 0;

    // Handle zero case
    if temp == 0 {
        return "0".to_string();
    }

    // Calculate length needed
    while temp != 0 {
        temp = temp / 10;
        len += 1;
        counter += 1;
        // There's 1 comma for first 4 digits, then 1 for every 3 after
        if counter == 4 {
            len += 1;
            counter = 1;
        }
    }

    let mut str_chars: Vec<char> = vec!['\0'; len];
    
    let stop = if num < 0 {
        len += 1; // +1 for the minus sign character
        str_chars.push('\0'); // Extend vector for minus sign
        str_chars[0] = '-';
        num = -num; // Make num positive for digit extraction
        1 // Start filling from index 1
    } else {
        0 // Start filling from index 0
    };

    let mut count = 0;
    let mut i = len - 1;
    
    while i >= stop {
        count += 1;
        if count == 4 {
            str_chars[i] = ',';
            count = 0;
            if i == 0 { break; }
            i -= 1;
            continue;
        }
        str_chars[i] = char::from_digit((num % 10) as u32, 10).unwrap();
        num = num / 10;
        if i == 0 { break; }
        i -= 1;
    }

    str_chars.into_iter().collect()
}

fn main() {
    let num = -10099870;
    let str_result = int_to_string(num);
    println!("{}", str_result);
}