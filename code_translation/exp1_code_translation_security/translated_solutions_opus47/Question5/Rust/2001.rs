fn int_to_string(a: i32) -> String {
    let mut result = String::new();
    
    let buf = a.to_string();
    let bytes = buf.as_bytes();
    let mut len = bytes.len();
    
    let actual_buf = if len > 0 && bytes[0] == b'-' {
        result.push('-');
        len -= 1;
        &bytes[1..]
    } else {
        bytes
    };
    
    for buf_idx in 0..len {
        if buf_idx != 0 && (len - buf_idx) % 3 == 0 {
            result.push(',');
        }
        result.push(actual_buf[buf_idx] as char);
    }
    
    result
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