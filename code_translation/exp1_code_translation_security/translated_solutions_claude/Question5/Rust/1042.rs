fn int_to_string(num: i32) -> String {
    let mut str_vec = vec![0u8; 100];
    let mut i = 0;
    let mut j = 0;
    let mut len = 0;
    let mut temp = num;
    let mut count = 0;
    
    // Calculate length
    while temp != 0 {
        temp = temp / 10;
        len += 1;
    }
    
    temp = num;
    
    // Build string with digits and commas
    while temp != 0 {
        str_vec[i] = (temp % 10) as u8 + b'0';
        temp = temp / 10;
        i += 1;
        count += 1;
        if count == 3 && temp != 0 {
            str_vec[i] = b',';
            i += 1;
            count = 0;
        }
    }
    
    // Null terminate (not needed in Rust but for consistency with C logic)
    str_vec[i] = 0;
    i -= 1;
    
    // Reverse the string
    while j < i {
        let temp_char = str_vec[j];
        str_vec[j] = str_vec[i];
        str_vec[i] = temp_char;
        j += 1;
        i -= 1;
    }
    
    // Convert to String, stopping at null terminator
    let end_pos = str_vec.iter().position(|&x| x == 0).unwrap_or(str_vec.len());
    String::from_utf8(str_vec[..end_pos].to_vec()).unwrap()
}

fn main() {
    println!("{}", int_to_string(7000000));
}