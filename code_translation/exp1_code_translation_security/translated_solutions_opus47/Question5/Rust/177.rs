fn int2str(num: i32) -> String {
    let s = num.to_string();
    let length = s.len();
    let bytes = s.as_bytes();
    
    // Compute length of result string (digits + needed commas)
    let newl = if length % 3 == 0 {
        length + length / 3 - 1
    } else {
        length + length / 3
    };
    
    let mut result = vec![0u8; newl];
    let mut counter = 0;
    let mut j = newl;
    
    for i in (0..length).rev() {
        counter += 1;
        j -= 1;
        result[j] = bytes[i];
        // Insert comma every 3 digits, but not at the very start
        if counter % 3 == 0 && i != 0 {
            j -= 1;
            result[j] = b',';
        }
    }
    
    String::from_utf8(result).unwrap()
}

fn main() {
    println!("{}", int2str(1000));      // "1,000"
    println!("{}", int2str(1000000));   // "1,000,000"
    println!("{}", int2str(100));       // "100"
    println!("{}", int2str(0));         // "0"
}