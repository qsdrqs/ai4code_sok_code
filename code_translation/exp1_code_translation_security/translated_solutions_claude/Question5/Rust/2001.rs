fn int_to_string(a: i32) -> String {
    let mut str = String::with_capacity(20);
    
    let buf = format!("{}", a);
    let len = buf.len();
    
    let (actual_buf, mut buf_idx) = if len > 0 && buf.starts_with('-') {
        str.push('-');
        (&buf[1..], 0)
    } else {
        (buf.as_str(), 0)
    };
    
    let actual_len = actual_buf.len();
    let chars: Vec<char> = actual_buf.chars().collect();
    
    while buf_idx < actual_len {
        if buf_idx != 0 && (actual_len - buf_idx) % 3 == 0 {
            str.push(',');
        }
        str.push(chars[buf_idx]);
        buf_idx += 1;
    }
    
    str
}

fn main() {
    println!("{}", int_to_string(7000000));
    println!("{}", int_to_string(700000));
    println!("{}", int_to_string(70000));
    println!("{}", int_to_string(7000));
    println!("{}", int_to_string(700));
    println!("{}", int_to_string(70));
    println!("{}", int_to_string(7));
    println!("{}", int_to_string(0));
    println!("{}", int_to_string(-7000000));
    println!("{}", int_to_string(-700000));
    println!("{}", int_to_string(-70000));
    println!("{}", int_to_string(-7000));
    println!("{}", int_to_string(-700));
    println!("{}", int_to_string(-70));
    println!("{}", int_to_string(-7));
    println!("{}", int_to_string(i32::MAX));
    println!("{}", int_to_string(i32::MIN));
}