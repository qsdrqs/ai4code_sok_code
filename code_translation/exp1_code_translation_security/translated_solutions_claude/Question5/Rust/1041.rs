fn main() {
    let num = 1000000u32;
    let str = add_commas(num);
    println!("{}", str);
}

fn reverse_str(s: &mut str) -> &mut str {
    let bytes = unsafe { s.as_bytes_mut() };
    let len = bytes.len();
    
    for i in 0..len/2 {
        bytes.swap(i, len - 1 - i);
    }
    
    s
}

fn add_commas(mut num: u32) -> String {
    if num == 0 {
        return "0".to_string();
    }
    
    let mut chars = Vec::new();
    let mut count = 0;
    
    while num != 0 {
        let digit = (num % 10) as u8 + b'0';
        chars.push(digit as char);
        num /= 10;
        count += 1;
        
        if count == 3 && num != 0 {
            chars.push(',');
            count = 0;
        }
    }
    
    let mut result: String = chars.into_iter().collect();
    unsafe {
        reverse_str(result.as_mut_str());
    }
    
    result
}